package com.ecommerce.portal.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class VendorRequestDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6069467599398353646L;
	/**
	 * Id will come at the time of validate store before add store method
	 */
	private Long id;

	private String email;

	private String password;

	private String name;

	private String contactNumber;

	private String citizenshipNumber;
}
