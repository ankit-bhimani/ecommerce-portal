/**
 *
 */
package com.ecommerce.portal.constant;

import java.util.HashMap;
import java.util.Map;

public enum OrdersStatus implements BasicStatus<OrdersStatus> {

	PENDING(Constant.PENDING, Constant.AVAILABLE), CONFIRMED(Constant.CONFIRMED, Constant.AVAILABLE), REJECTED(Constant.REJECTED, Constant.AVAILABLE),
	IN_PROCESS(Constant.IN_PROCESS, Constant.RESERVED), ORDER_IS_PREPARED(Constant.ORDER_IS_PREPARED, Constant.RESERVED),
	ORDER_PICKED_UP(Constant.ORDER_PICKED_UP, Constant.RESERVED), DELIVERED(Constant.DELIVERED, Constant.DELIVERED),
	CANCELLED(Constant.CANCELLED, Constant.AVAILABLE);

	String statusValue;
	String stockStatus;

	/**
	 *
	 */
	private OrdersStatus(final String statusValue, final String stockValue) {
		this.statusValue = statusValue;
		stockStatus = stockValue;
	}

	@Override
	public String getStatusValue() {
		return statusValue;
	}

	public String getStockValue() {
		return stockStatus;
	}

	private static final Map<String, OrdersStatus> ORDER_STATUS = new HashMap<>();
	static {
		for (final OrdersStatus orderStatus : values()) {
			ORDER_STATUS.put(orderStatus.getStatusValue(), orderStatus);
		}
	}

	public static OrdersStatus getByValue(final String value) {
		return ORDER_STATUS.get(value);
	}

	@Override
	public BasicStatus<OrdersStatus>[] nextStatus() {
		OrdersStatus[] nextStatus = null;
		switch (this) {
		case PENDING:
			nextStatus = new OrdersStatus[] { CONFIRMED, CANCELLED, REJECTED, };
			break;
		case CONFIRMED:
			nextStatus = new OrdersStatus[] { IN_PROCESS };
			break;
		case IN_PROCESS:
			nextStatus = new OrdersStatus[] { ORDER_IS_PREPARED, ORDER_PICKED_UP };
			break;
		case ORDER_IS_PREPARED:
			nextStatus = new OrdersStatus[] { ORDER_PICKED_UP };
			break;
		case ORDER_PICKED_UP:
			nextStatus = new OrdersStatus[] { DELIVERED };
			break;
		case DELIVERED:
			nextStatus = new OrdersStatus[] {};
			break;
		case REJECTED:
			nextStatus = new OrdersStatus[] {};
			break;
		default:
			nextStatus = new OrdersStatus[] {};
		}
		return nextStatus;
	}

	public boolean contains(final String newStatus) {
		for (final BasicStatus<OrdersStatus> status : nextStatus()) {
			if (newStatus.equals(status.getStatusValue())) {
				return true;
			}
		}
		return false;
	}

}
