package com.ecommerce.portal.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "orders")
@Data
@EqualsAndHashCode(callSuper = false)
public class Orders extends CommonModel {

	/**
	 *
	 */
	private static final long serialVersionUID = 63234495690384066L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	/**
	 * Contains Order Status
	 */
	@Column(name = "status", nullable = false)
	private String status;

	/**
	 * paymentMode will be online or COD
	 */
	@Column(name = "payment_mode", nullable = false)
	private String paymentMode;

	/**
	 * from parcel cart
	 */
	@Column(name = "pickup_address")
	private String pickupAddress;

	@Column(name = "pickup_latitude")
	private BigDecimal pickupLatitude;

	@Column(name = "pickup_longitude")
	private BigDecimal pickupLongitude;

	@Column(name = "pickup_contact_name")
	private String pickupContactName;

	@Column(name = "pickup_contact_number")
	private String pickupContactNumber;

	@Column(name = "shipping_address", nullable = false)
	private String shippingAddress;

	@Column(name = "shipping_latitude", nullable = false)
	private BigDecimal shippingLatitude;

	@Column(name = "shipping_longitude", nullable = false)
	private BigDecimal shippingLongitude;

	@Column(name = "shipping_contact_name")
	private String shippingContactName;

	@Column(name = "shipping_contact_number")
	private String shippingContactNumber;

	@Column(name = "delivery_charge")
	private Double deliveryCharge;

	/**
	 * grossOrderAmount +- changes in order during orderCycle
	 */
	@Column(name = "gross_order_amount", nullable = false)
	private Double grossOrderAmount;

	/**
	 * totalOrderAmountAtOrder +- changes in order during orderCycle
	 */
	@Column(name = "total_order_amount", nullable = false)
	private Double totalOrderAmount;

	/**
	 * totalPayableAmountAtOrder + if any post payment
	 */
	@Column(name = "total_payable_amount", nullable = false)
	private Double totalPayableAmount;

	@Column(name = "cancel_reason", nullable = true)
	private String cancelReason;

	@Column(name = "cancel_description", nullable = true)
	private String cancelDescription;

	@Column(name = "special_instruction", nullable = true)
	private String specialInstruction;

	@Column(name = "distance", nullable = false)
	private Double distance;

	@Column(name = "delivery_date", nullable = true)
	private Date deliveryDate;

	@Column(name = "payment_date", nullable = true)
	private Date paymentDate;

	@Column(name = "order_date", nullable = true)
	private Date orderDate;

}
