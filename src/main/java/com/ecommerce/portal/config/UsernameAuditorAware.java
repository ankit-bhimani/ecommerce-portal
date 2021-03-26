/**
 *
 */
package com.ecommerce.portal.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ecommerce.portal.constant.Constant;

public class UsernameAuditorAware implements AuditorAware<Long> {

	@Override
	public Optional<Long> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			return Optional.ofNullable(1L);
		}

		if (authentication.getPrincipal().equals(Constant.ANONYMOUS_USER)) {
			return Optional.ofNullable(1L);
		}

		return Optional.ofNullable(((UserAwareUserDetails) authentication.getPrincipal()).getUser().getId());
	}
}
