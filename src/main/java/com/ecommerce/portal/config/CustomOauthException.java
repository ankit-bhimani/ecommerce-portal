package com.ecommerce.portal.config;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {
	/**
	 *
	 */
	private static final long serialVersionUID = 3149581648772863690L;

	public CustomOauthException(final String msg) {
		super(msg);
	}
}