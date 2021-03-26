package com.ecommerce.portal.service;

import java.util.Optional;

import com.ecommerce.portal.dto.LoginResponse;
import com.ecommerce.portal.dto.UserLoginDto;
import com.ecommerce.portal.exception.NotFoundException;
import com.ecommerce.portal.exception.UnAuthorizationException;
import com.ecommerce.portal.exception.ValidationException;
import com.ecommerce.portal.model.UserLogin;

public interface UserLoginService {

	Optional<UserLogin> getUserLogin(Long id);

	/**
	 * Get user login details by user id
	 *
	 * @param  userId
	 * @return
	 * @throws NotFoundException
	 */
	UserLogin getUserLoginDetails(Long userId) throws NotFoundException;

	/**
	 * @param  userLoginDto
	 * @return
	 * @throws NotFoundException
	 * @throws UnAuthorizationException
	 * @throws ValidationException
	 */
	LoginResponse checkUserLogin(UserLoginDto userLoginDto) throws NotFoundException, UnAuthorizationException, ValidationException;
}
