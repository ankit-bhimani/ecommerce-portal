package com.ecommerce.portal.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ecommerce.portal.dto.CategoryDTO;
import com.ecommerce.portal.locale.MessageByLocaleService;
import com.ecommerce.portal.service.CategoryService;

@Component
public class CategoryValidator implements Validator {

	@Autowired
	private MessageByLocaleService messageByLocaleService;

	@Autowired
	private CategoryService categoryService;

	@Override
	public boolean supports(final Class<?> clazz) {
		return CategoryDTO.class.equals(clazz);
	}

	/**
	 * purpose - to validate object and apply various validations. this method may carry number of validation conditions.
	 */
	@Override
	public void validate(final Object target, final Errors errors) {
		final CategoryDTO categoryDTO = (CategoryDTO) target;
		/**
		 * to check category duplication
		 */
		if (categoryDTO.getName() != null && categoryService.isCategoryExists(categoryDTO)) {
			errors.rejectValue("name", "409", messageByLocaleService.getMessage("category.name.not.unique", null));
		}
	}
}
