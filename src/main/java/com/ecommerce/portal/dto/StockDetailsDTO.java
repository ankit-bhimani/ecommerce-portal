package com.ecommerce.portal.dto;

import java.io.Serializable;

import lombok.Data;

/**
 *
 * @author : ankit bhimani
 * @date   : Mar 27, 2021
 */
@Data
public class StockDetailsDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8845840813113451281L;
	private Long id;
	private Long productId;
	private Double available;
	private String productName;
	private Long uomId;
	private String uomLabel;
	private Double reserved;
	private Double delivered;
}
