package com.ecommerce.portal.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author : Kody Technolab PVT. LTD.
 * @date   : 22-Jun-2020
 */
@Data
public class LoginRequestDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -9129942395904924485L;

	/**
	 * Depending upon the grant type either we have userName and password or we have a refreshToken
	 */

	/**
	 * Either password or refresh_token
	 */
	private String grantType;

	@JsonProperty("refresh_token")
	private String refreshToken;

	private String userName;

	private String password;

}
