package com.ecommerce.portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.portal.model.Product;
import com.ecommerce.portal.model.StockDetails;

/**
 *
 * @author :ankit bhimani
 * @date   : Mar 27, 2021
 */
@Repository
public interface StockDetailsRepository extends JpaRepository<StockDetails, Long> {

	/**
	 * find by product
	 *
	 * @param  product
	 * @return
	 */
	Optional<StockDetails> findByProduct(Product product);

}
