package com.ecommerce.portal.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AddStockDto implements Serializable {
	/**
	*
	*/
	private static final long serialVersionUID = 7200203625064820144L;

	@NotNull(message = "{product.variant.id.not.null}")
	private Long productVariantId;
	@NotNull(message = "{invalid.update.qty}")
	private Long totalQty;
}
