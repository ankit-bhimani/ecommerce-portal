/**
 *
 */
package com.ecommerce.portal.constant;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EntityType {

	/**
	 * Here USER There will be 2 types of users included : SUPER ADMIN & ADMIN USERS
	 */
	SUPER_ADMIN("SUPER_ADMIN"), CUSTOMER("CUSTOMER"), VENDOR("VENDOR"),;

	String statusValue;

	private static final Map<String, EntityType> ENTITY_TYPE_MAP = new HashMap<>();
	static {
		for (final EntityType entityType : values()) {
			ENTITY_TYPE_MAP.put(entityType.getStatusValue(), entityType);
		}
	}

	public static EntityType getByValue(final String value) {
		return ENTITY_TYPE_MAP.get(value);
	}

}
