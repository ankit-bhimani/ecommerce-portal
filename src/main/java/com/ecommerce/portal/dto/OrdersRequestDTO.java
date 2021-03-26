/**
 *
 */
package com.ecommerce.portal.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * @author : Kody Technolab PVT. LTD.
 * @date   : 07-Jul-2020
 */
@Data
public class OrdersRequestDTO implements Serializable {
	/**
	*
	*/
	private static final long serialVersionUID = 3284884077855088331L;

	private Long customerId;

	/**
	 * Send package Either pickupAddressId or Other fields for pickup address
	 */
	private Long pickupAddressId;

	private String pickupAddress;

	private BigDecimal pickupLatitude;

	private BigDecimal pickupLongitude;

	private String pickupStreetNo;

	private String pickupBuildingName;

	private String pickupLandmark;

	private Long pickupCityId;

	private String pickupArea;

	private String pickupContactName;

	private String pickupContactNumber;

	/**
	 * Send package Either shippingAddressId or Other fields for shipping address
	 */
	private Long shippingAddressId;

	private BigDecimal shippingLatitude;

	private BigDecimal shippingLongitude;

	private String shippingStreetNo;

	private String shippingBuildingName;

	private String shippingLandmark;

	private Long shippingCityId;

	private String shippingArea;

	private String shippingContactName;

	private String shippingContactNumber;

	/**
	 * For send package parcel content Id
	 */
	private Long parcelContentId;

	/**
	 * Below 3 fields for online payment
	 */
	private String transactionId;

	private String cartReferenceId;

	/**
	 * Added For offer code
	 */
	private String offerCode;

	/**
	 * orderType Either Store Delivery or Send Package
	 */
	private String orderType;

	/**
	 * special request for the order
	 */
	private String specialInstruction;

	private Double totalOrderAmount;

	private String paymentMode;

	private Date scheduleDeliveryDate;

	/**
	 * Whether rewards is used or not
	 */
	private Boolean rewardFreeDelivery;

	/**
	 * This flag will be used to indicate if the wallet needs to be used for this order or not. This flag if true can have following two cases: 1. Entire order
	 * amount would be settled from wallet balance. 2. Partial order amount is settled via wallet and other amount would either be COD or online payment
	 * depending upon the payment mode selected
	 */
	private Boolean useWallet;

	/**
	 * Wallet contribution will be used internally
	 */
	private Double walletContribution;

	/**
	 * If payment is online then at the time of create order shipping address will be set from online cart
	 */
	private String shippingAddress;

	/**
	 * It will use at the time of give tip by customer
	 */
	private Double tip;
	private Long ordersId;
}
