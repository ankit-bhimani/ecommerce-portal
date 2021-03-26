/**
 *
 */
package com.ecommerce.portal.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 *
 * @author : ankit bhimani
 * @date   : Mar 26, 2021
 */
@Data
public class ProductRequestDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5565429810225875802L;
	private Long id;

	@NotBlank(message = "{name.not.null}")
	private String name;

	@NotBlank(message = "{description.not.null}")
	private String description;

	@NotNull(message = "{category.id.not.null}")
	private Long categoryId;

	@NotNull(message = "{vendor.id.not.null}")
	private Long vendorId;
}
