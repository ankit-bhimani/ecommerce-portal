
package com.ecommerce.portal.constant;

public final class Constant {

	private Constant() {
		super();
	}

	public static final String GRANT_TYPE_PASSWORD = "password";
	public static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";
	public static final String CLIENT_ID = "ecommerce-client";
	public static final String SECRET_ID = "ecommerce-secret";
	public static final String ANONYMOUS_USER = "anonymousUser";

	public static final String AVAILABLE = "Available";
	public static final String RESERVED = "Reserved";
	public static final String DELIVERED = "Delivered";

	public static final String PENDING = "Pending";
	public static final String CONFIRMED = "Confirmed";
	public static final String IN_PROCESS = "In-Process";
	public static final String CANCELLED = "Cancelled";
	public static final String REJECTED = "Rejected";
	public static final String ORDER_IS_PREPARED = "Order Is Prepared";
	public static final String ORDER_PICKED_UP = "Order Picked Up";
}
