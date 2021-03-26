/**
 *
 */
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
@Data
@Entity
@Table(name = "product_variant")
@EqualsAndHashCode(callSuper = false)
public class ProductVariant extends CommonModel {
	/**
	*
	*/
	private static final long serialVersionUID = 4323772162488565443L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@Column(name = "sku", nullable = false)
	private String sku;

	@Column(name = "image", nullable = false)
	private String image;

	@Column(name = "price", nullable = false)
	private Double price;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "uom_id", nullable = false)
	private UOM uom;

}
