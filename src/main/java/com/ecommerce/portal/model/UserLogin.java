package com.ecommerce.portal.model;

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

/**
 *
 * @author : ankit bhimani
 * @date   : Mar 26, 2021
 */
@Entity
@Table(name = "user_login")
@Data
@EqualsAndHashCode(callSuper = false)
public class UserLogin extends CommonModel {

	/**
	 *
	 */
	private static final long serialVersionUID = -8712594030416874969L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "entity_id")
	private Long entityId;

	@Column(name = "entity_type")
	private String entityType;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "contact_number")
	private String contactNumber;

	@Column(name = "password")
	private String password;

	@JoinColumn(name = "role_id", nullable = false, columnDefinition = "BIGINT default 1")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Role role;

}
