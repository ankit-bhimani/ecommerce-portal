package com.ecommerce.portal.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class CustomerResponseDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6468554264495057699L;
	private Long id;
	private String name;
	private String email;
	private String contactNumber;
	private String gender;
	private Boolean active;
	private Date createdAt;
	private Long userId;
}
