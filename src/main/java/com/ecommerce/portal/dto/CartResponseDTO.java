package com.ecommerce.portal.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CartResponseDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8303058211358351890L;

	/**
	 * Contains order amount after discount, before applying delivery charge and wallet deductions (Summation of store total)
	 */
	private Double grossOrderAmount;
}