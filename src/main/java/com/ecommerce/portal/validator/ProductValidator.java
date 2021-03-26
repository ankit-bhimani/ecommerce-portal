/**
 *
 */
package com.ecommerce.portal.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ecommerce.portal.dto.ProductRequestDTO;
import com.ecommerce.portal.locale.MessageByLocaleService;
import com.ecommerce.portal.service.ProductService;

@Component
public class ProductValidator implements Validator {

	@Autowired
	private ProductService productService;

	@Autowired
	private MessageByLocaleService messageByLocaleService;

	@Override
	public boolean supports(final Class<?> clazz) {
		return ProductRequestDTO.class.equals(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {

		if (target instanceof ProductRequestDTO) {
			ProductRequestDTO productRequestDto = (ProductRequestDTO) target;

			if (productRequestDto.getName() != null && productService.checkIfProductExists(productRequestDto)) {
				errors.rejectValue("name", "409", messageByLocaleService.getMessage("product.already.exists", new Object[] { productRequestDto.getName() }));
			}
		}

	}

}
