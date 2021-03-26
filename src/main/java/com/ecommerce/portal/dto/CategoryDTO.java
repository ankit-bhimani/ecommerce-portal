package com.ecommerce.portal.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 *
 * @author : ankit bhimani
 * @date   : Mar 26, 2021
 */
@Data
public class CategoryDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7947238128297093026L;

	private Long id;

	@NotBlank(message = "{name.not.null}")
	private String name;

	private Boolean active;
}
