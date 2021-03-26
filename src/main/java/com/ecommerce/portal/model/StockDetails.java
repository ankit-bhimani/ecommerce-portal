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
 * @author :ankit bhimani
 * @date   : Mar 27, 2021
 */
@Entity
@Table(name = "stock_details")
@Data
@EqualsAndHashCode(callSuper = false)
public class StockDetails extends CommonModel {

	/**
	 *
	 */
	private static final long serialVersionUID = -8321363361424422271L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@JoinColumn(name = "product_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	private Product product;

	@Column(name = "available")
	private Double available;

	@Column(name = "reserved")
	private Double reserved;

	@Column(name = "delivered")
	private Double delivered;

}
