package com.ecommerce.portal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "vendor")
public class Vendor extends CommonModel {

	/**
	 *
	 */
	private static final long serialVersionUID = 2647069390216315468L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "contact_number", nullable = false)
	private String contactNumber;

	@Column(name = "citizenship_number", nullable = false)
	private String citizenshipNumber;

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "email_verified", nullable = false)
	private Boolean emailVerified;

	@Column(name = "contact_verified", nullable = false)
	private Boolean contactVerified;

}
