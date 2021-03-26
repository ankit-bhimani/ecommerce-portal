/**
 *
 */
package com.ecommerce.portal.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserLoginDto implements Serializable {

	private static final long serialVersionUID = -7133474062798438886L;
	private String email;
	@NotNull(message = "{password.not.null}")
	private String password;

	/**
	 * CUSTOMER,VENDOR
	 */
	private String userType;
	private Long entityId;
	private Long userId;
}
