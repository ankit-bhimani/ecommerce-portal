package com.ecommerce.portal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author : ankit bhimani
 * @date   : Mar 26, 2021
 */
@Entity
@Table(name = "customer")
@Data
@EqualsAndHashCode(callSuper = false)
public class Customer extends CommonModel {

	private static final long serialVersionUID = 3240506949329976947L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "contact_number", unique = true)
	private String contactNumber;

}