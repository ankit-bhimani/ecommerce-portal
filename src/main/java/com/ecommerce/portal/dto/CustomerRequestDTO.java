package com.ecommerce.portal.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CustomerRequestDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8559207854774229436L;

	@NotBlank(message = "{name.not.null}")
	private String name;

	@NotBlank(message = "{email.not.null}")
	private String email;

	@NotBlank(message = "{contact.number.not.null}")
	private String contactNumber;

	private String password;

	private String gender;

	private Date birthDate;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerRequestDTO [name=").append(name).append(", email=").append(email).append(", contactNumber=").append(contactNumber)
				.append(", gender=").append(gender).append(", birthDate=").append(birthDate).append("]");
		return builder.toString();
	}

}