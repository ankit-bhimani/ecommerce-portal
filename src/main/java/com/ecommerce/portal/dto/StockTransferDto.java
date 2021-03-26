package com.ecommerce.portal.dto;

import java.io.Serializable;

import lombok.Data;

/**
 *
 * @author :ankit bhimani
 * @date   : Mar 27, 2021
 */

@Data
public class StockTransferDto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6209568810578393309L;

	private String transferedTo;
	private String transferedFrom;
	private Long quantity;
	private Long productId;
	private Long orderId;
}
