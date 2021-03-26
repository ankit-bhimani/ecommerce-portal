package com.ecommerce.portal.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ecommerce.portal.dto.CustomerRequestDTO;
import com.ecommerce.portal.exception.ValidationException;
import com.ecommerce.portal.locale.MessageByLocaleService;
import com.ecommerce.portal.service.CustomerService;

@Component
public class CustomerValidator implements Validator {

	/**
	 * Locale message service - to display response messages from messages_en.properties
	 */
	@Autowired
	private MessageByLocaleService messageByLocaleService;

	@Autowired
	private CustomerService customerService;

	@Override
	public boolean supports(final Class<?> clazz) {
		return CustomerRequestDTO.class.equals(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {
		if (target instanceof CustomerRequestDTO) {
			final CustomerRequestDTO customerRequestDTO = (CustomerRequestDTO) target;

			try {
				if (customerRequestDTO.getContactNumber() != null && customerService.isExists(customerRequestDTO)) {
					errors.rejectValue("contactNumber", "409", messageByLocaleService.getMessage("customer.contact.exists", null));
				}
			} catch (ValidationException e) {
				errors.rejectValue("contactNumber", "409", e.getMessage());
			}

			try {
				if (customerRequestDTO.getEmail() != null && customerService.isEmailExists(customerRequestDTO)) {
					errors.rejectValue("email", "409", messageByLocaleService.getMessage("customer.email.exists", null));
				}
			} catch (ValidationException e) {
				errors.rejectValue("email", "409", e.getMessage());
			}
		}
	}
}