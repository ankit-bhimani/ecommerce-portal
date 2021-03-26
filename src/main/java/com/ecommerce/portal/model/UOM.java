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
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "uom")
@Entity()
public class UOM extends CommonModel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1767406305449061060L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "uom_label")
	private String uomLabel;

	@Column(name = "store_id", nullable = false)
	private Long storeId;

}
