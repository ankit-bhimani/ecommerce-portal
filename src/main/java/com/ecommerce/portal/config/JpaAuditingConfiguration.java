/**
 *
 */
package com.ecommerce.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfiguration {

	@Bean
	public UsernameAuditorAware auditorProvider() {
		return new UsernameAuditorAware();
	}
}
