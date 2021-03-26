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
 * @author : Kody Technolab PVT. LTD.
 * @date   :23-Dec-2020
 */
@Table(name = "role")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends CommonModel {

	/**
	 *
	 */
	private static final long serialVersionUID = -7915137780231594263L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String userType;

	private String description;

	@Column(name = "is_default", columnDefinition = "boolean default false")
	private Boolean isDefault;

}
