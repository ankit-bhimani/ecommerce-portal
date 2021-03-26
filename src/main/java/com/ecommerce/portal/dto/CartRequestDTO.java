package com.ecommerce.portal.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 *
 * @author : Kody Technolab PVT. LTD.
 * @date   :02-Feb-2021
 */
@Data
public class CartRequestDTO implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = 2920180246659376736L;

	private Long id;

	private String uuid;

	@NotNull(message = "{variant.id.not.null}")
	private Long productVariantId;

	@NotNull(message = "{quantity.not.null}")
	private Long quantity;
	/**
	 * This is added for temporary applying buy one get one offer from out side
	 */
	private Boolean applyOffer;
	/**
	 * If there is discount available on product variant and customer have applied the same then flag will be true 
	 */
	private Boolean applyDiscount;
}