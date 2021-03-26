package com.ecommerce.portal.service.impl;

import java.util.Arrays;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.portal.config.UserAwareUserDetails;
import com.ecommerce.portal.constant.Constant;
import com.ecommerce.portal.constant.EntityType;
import com.ecommerce.portal.dto.LoginRequestDTO;
import com.ecommerce.portal.dto.LoginResponse;
import com.ecommerce.portal.dto.UserLoginDto;
import com.ecommerce.portal.exception.BaseRuntimeException;
import com.ecommerce.portal.exception.NotFoundException;
import com.ecommerce.portal.exception.UnAuthorizationException;
import com.ecommerce.portal.exception.ValidationException;
import com.ecommerce.portal.locale.MessageByLocaleService;
import com.ecommerce.portal.model.Customer;
import com.ecommerce.portal.model.UserLogin;
import com.ecommerce.portal.model.Vendor;
import com.ecommerce.portal.repository.UserLoginRepository;
import com.ecommerce.portal.service.CustomerService;
import com.ecommerce.portal.service.RoleService;
import com.ecommerce.portal.service.UserLoginService;
import com.ecommerce.portal.service.VendorService;

@Service(value = "userLoginService")
@Transactional(rollbackFor = Throwable.class)
public class UserLoginServiceImpl implements UserLoginService, UserDetailsService {

	/**
	 *
	 */
	private static final String USER_NOT_FOUND_USERNAME = "user.not.found.username";

	/**
	 *
	 */
	private static final String USER_ACCOUNT_UNAUTHORIZED_ADMIN = "user.account.unauthorized.admin";

	/**
	 *
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginServiceImpl.class);

	@Autowired
	private UserLoginRepository userLoginRepository;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private VendorService vendorService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private MessageByLocaleService messageByLocaleService;

	@Value("${local.service.url}")
	private String localServiceUrl;

	@Override
	public UserDetails loadUserByUsername(final String username) {
		String actualUser = null;
		String actualUserLoginWith = null;
		String loginWith = null;
		String actualUserWithType = null;
		String entityType = null;
		if (username != null && username.contains("#")) {
			actualUserWithType = username.split("#")[0];
		} else {
			actualUserWithType = username;
		}

		/**
		 * Check if the user name contains the role , if not throw error
		 */
		if (actualUserWithType != null && actualUserWithType.contains("!!")) {
			actualUserLoginWith = actualUserWithType.split("!!")[0];
			entityType = actualUserWithType.split("!!")[1];
		} else {
			throw new BaseRuntimeException(HttpStatus.UNAUTHORIZED, messageByLocaleService.getMessage("specify.role", new Object[] {}));
		}

		/**
		 * Check if the user name whether login with email or contact number , if not throw error
		 */
		if (actualUserLoginWith != null && actualUserLoginWith.contains("~")) {
			actualUser = actualUserLoginWith.split("~")[0];
			loginWith = actualUserLoginWith.split("~")[1];
		} else {
			throw new BaseRuntimeException(HttpStatus.UNAUTHORIZED, messageByLocaleService.getMessage("specify.login.with", new Object[] {}));
		}

		Optional<UserLogin> optUserLogin = userLoginRepository.findByEmailAndEntityType(actualUser, entityType);

		/**
		 * If the userType is USERS and optUserLogin is not available, the user might be a superadmin, check if the user is superadmin.
		 */
		if (!optUserLogin.isPresent()) {
			try {
				optUserLogin = userLoginRepository.findByEmailAndRole(actualUser, roleService.getRoleDetailByName(EntityType.SUPER_ADMIN.getStatusValue()));

			} catch (NotFoundException e) {
				LOGGER.error("SUPER_ADMIN role not found");
			}
		} else {
			if (!optUserLogin.isPresent()) {
				throw new BaseRuntimeException(HttpStatus.UNAUTHORIZED, messageByLocaleService.getMessage("invalid.username", null));
			} else {
				validationAllUsers(actualUser, loginWith, optUserLogin.get());
			}
		}

		/**
		 * If user is not exists then throw an error
		 */
		if (!optUserLogin.isPresent()) {
			throw new BaseRuntimeException(HttpStatus.UNAUTHORIZED, messageByLocaleService.getMessage("invalid.username", null));
		} else {
			return generateTokenDetails(actualUserWithType, optUserLogin.get());
		}
	}

	/**
	 * @param actualUser
	 * @param loginWith
	 * @param requestVia
	 * @param userLogin
	 */
	private void validationAllUsers(final String actualUser, final String loginWith, final UserLogin userLogin) {

		/**
		 * If user is not active then possible 2 cases </br>
		 * 1.User is not activated yet. </br>
		 * 2.User is deactivated by Administrator </br>
		 * If user is active still there is possibility that suppose user is active but mobile is not verified then we are not allowed to login and viceversa
		 */
		if (userLogin.getEntityType().equals(EntityType.CUSTOMER.getStatusValue())) {
			customerLoginValidation(actualUser, loginWith, userLogin);
		} else if (userLogin.getEntityType().equals(EntityType.VENDOR.getStatusValue())) {
			vendorLoginValidation(actualUser, loginWith, userLogin);
		} else {
			throw new BaseRuntimeException(HttpStatus.UNAUTHORIZED, messageByLocaleService.getMessage(USER_ACCOUNT_UNAUTHORIZED_ADMIN, null));
		}
	}

	/**
	 * Vendor's related validation for login
	 *
	 * @param actualUser
	 * @param loginWith
	 * @param userLogin
	 */
	private void vendorLoginValidation(final String actualUser, final String loginWith, final UserLogin userLogin) {
		Vendor vendor = null;
		try {
			vendor = vendorService.getVendorDetails(userLogin.getEntityId());
		} catch (final NotFoundException e) {
			LOGGER.error("Vendor not found for vendor Id: {} ", userLogin.getEntityId());
		}
		if (vendor == null) {
			throw new BaseRuntimeException(HttpStatus.UNAUTHORIZED,
					messageByLocaleService.getMessage("vendor.not.found.username", new Object[] { actualUser }));
		}
	}

	/**
	 * @param  actualUserWithType
	 * @param  requestVia
	 * @param  userLogin
	 * @return
	 */
	private UserDetails generateTokenDetails(final String actualUserWithType, final UserLogin userLogin) {
		final String role = userLogin.getRole().getName();
		final SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
		if (userLogin.getPassword() == null) {
			throw new BaseRuntimeException(HttpStatus.UNAUTHORIZED, messageByLocaleService.getMessage("user.unauthorized.social", null));
		}
		return new UserAwareUserDetails(actualUserWithType, userLogin.getPassword(), Arrays.asList(authority), userLogin);
	}

	/**
	 * Customer's related validation for login
	 *
	 * @param loginWith
	 * @param actualUser
	 * @param userLogin
	 */
	private void customerLoginValidation(final String actualUser, final String loginWith, final UserLogin userLogin) {
		Customer customer = null;
		try {
			customer = customerService.getCustomerDetails(userLogin.getEntityId());
		} catch (final NotFoundException e) {
			LOGGER.error("Customer not found for customer Id: {} ", userLogin.getEntityId());
		}
		/**
		 * If customer not found which can not be possible until any unusual activity in database
		 */
		if (customer == null) {
			throw new BaseRuntimeException(HttpStatus.UNAUTHORIZED,
					messageByLocaleService.getMessage("customer.not.found.username", new Object[] { actualUser }));
		}
	}

	@Override
	public Optional<UserLogin> getUserLogin(final Long id) {
		return userLoginRepository.findById(id);
	}

	/**
	 * Common method for user login
	 *
	 * @param  userLoginDto
	 * @return
	 * @throws NotFoundException
	 * @throws UnAuthorizationException
	 * @throws ValidationException
	 */
	@Override
	public LoginResponse checkUserLogin(final UserLoginDto userLoginDto) throws NotFoundException, UnAuthorizationException, ValidationException {
		final LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
		loginRequestDTO.setGrantType(Constant.GRANT_TYPE_PASSWORD);
		if (userLoginDto.getEmail() != null) {
			loginRequestDTO
					.setUserName(userLoginDto.getEmail().toLowerCase().concat("~").concat("EMAIL").concat("!!").concat(userLoginDto.getUserType()).concat("#"));
		}

		loginRequestDTO.setPassword(userLoginDto.getPassword());
		LoginResponse loginResponse = generateAuthToken(localServiceUrl, loginRequestDTO);

		getUserInfo(loginResponse, userLoginDto);

		return loginResponse;
	}

	/**
	 * Internally generate token based on refresh token or userName & password
	 *
	 * @param  url
	 * @param  loginRequestDTO
	 * @return
	 * @throws UnAuthorizationException
	 * @throws NotFoundException
	 */
	private LoginResponse generateAuthToken(final String url, final LoginRequestDTO loginRequestDTO) throws UnAuthorizationException {

		RestTemplate restTemplate = null;
		LoginResponse result = null;
		MultiValueMap<String, String> map = null;
		HttpHeaders headers = null;

		ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());

		restTemplate = new RestTemplate(requestFactory);

		String plainCreds = Constant.CLIENT_ID + ":" + Constant.SECRET_ID;
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);

		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.add("Authorization", "Basic " + base64Creds);

		map = new LinkedMultiValueMap<>();
		map.add("grant_type", loginRequestDTO.getGrantType());
		map.add("username", loginRequestDTO.getUserName());
		map.add("password", loginRequestDTO.getPassword());
		map.add("refresh_token", loginRequestDTO.getRefreshToken());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		String outhURL = url + "oauth/token";
		ResponseEntity<LoginResponse> response = restTemplate.postForEntity(outhURL, request, LoginResponse.class);
		result = response.getBody();
		if (result.getStatus() == HttpStatus.UNAUTHORIZED.value()) {
			throw new UnAuthorizationException(result.getMessage());
		} else {
			result.setMessage("login successfully");
			result.setStatus(200);
		}
		return result;
	}

	/**
	 * Get user info based on userLogin request DTO
	 *
	 * @param  loginResponse
	 * @param  userLoginDto
	 * @return
	 * @throws NotFoundException
	 */
	private LoginResponse getUserInfo(final LoginResponse loginResponse, final UserLoginDto userLoginDto) throws NotFoundException {
		Optional<UserLogin> optUserLogin;
		optUserLogin = userLoginRepository.findByEmailAndEntityType(userLoginDto.getEmail(), userLoginDto.getUserType());
		optUserLogin = setBasicInfo(loginResponse, userLoginDto, optUserLogin);

		return loginResponse;
	}

	private Optional<UserLogin> setBasicInfo(final LoginResponse loginResponse, final UserLoginDto userLoginDto, Optional<UserLogin> optUserLogin)
			throws NotFoundException {
		if (optUserLogin.isPresent()) {
			BeanUtils.copyProperties(optUserLogin.get(), loginResponse);
			loginResponse.setUserId(optUserLogin.get().getId());
			loginResponse.setRoleId(optUserLogin.get().getRole().getId());
			loginResponse.setRoleName(optUserLogin.get().getRole().getName());
			if (EntityType.CUSTOMER.getStatusValue().equals(optUserLogin.get().getEntityType())) {
				Customer customer = customerService.getCustomerDetails(optUserLogin.get().getEntityId());
				BeanUtils.copyProperties(customer, loginResponse);
			} else if (EntityType.VENDOR.getStatusValue().equals(optUserLogin.get().getEntityType())) {
				Vendor vendor = vendorService.getVendorDetails(optUserLogin.get().getEntityId());
				BeanUtils.copyProperties(vendor, loginResponse);
			} else {
				BeanUtils.copyProperties(optUserLogin.get(), loginResponse);
				loginResponse.setUserId(optUserLogin.get().getId());
				loginResponse.setName("Super Admin");
				loginResponse.setRoleId(optUserLogin.get().getRole().getId());
				loginResponse.setRoleName(optUserLogin.get().getRole().getName());
			}
		} else {
			optUserLogin = userLoginRepository.findByEmailAndRole(userLoginDto.getEmail().toLowerCase(),
					roleService.getRoleDetailByName(EntityType.SUPER_ADMIN.getStatusValue()));
		}

		if (optUserLogin.isPresent()) {
			BeanUtils.copyProperties(optUserLogin.get(), loginResponse);
			loginResponse.setUserId(optUserLogin.get().getId());
			loginResponse.setName("Super Admin");
			loginResponse.setRoleId(optUserLogin.get().getRole().getId());
			loginResponse.setRoleName(optUserLogin.get().getRole().getName());
		}
		return optUserLogin;
	}

	@Override
	public UserLogin getUserLoginDetails(final Long userId) throws NotFoundException {
		return userLoginRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException(messageByLocaleService.getMessage("user.not.found", new Object[] { userId })));
	}
}
