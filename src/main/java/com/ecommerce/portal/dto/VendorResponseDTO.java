/**
 *
 */
package com.ecommerce.portal.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class VendorResponseDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -3419032313480089879L;

	private Long id;

	private String name;

	private String email;

	private String citizenshipNumber;

	private Boolean emailVerified;

	private String contactNumber;

	private Boolean contactVerified;

	private String status;

	private Boolean active;

	private Date createdAt;

	private Long userId;

	private List<String> nextStatus;

	private Integer noOfStores;
}
