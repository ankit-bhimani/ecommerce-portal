/**
 *
 */
package com.ecommerce.portal.dto;

import java.io.Serializable;

import lombok.Data;

/**
 *
 * @author : ankit bhimani
 * @date   : Mar 26, 2021
 */
@Data
public class ProductResponseDTO implements Serializable {
	/**
	*
	*/
	private static final long serialVersionUID = 6390703178808783815L;

	private Long id;
	private String name;
	private Long categoryId;
	private String categoryName;
	private Boolean active;
	private Long vendorId;
	private String vendorName;
}
