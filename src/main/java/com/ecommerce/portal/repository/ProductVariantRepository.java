/**
 *
 */
package com.ecommerce.portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.portal.model.Product;
import com.ecommerce.portal.model.ProductVariant;
import com.ecommerce.portal.model.UOM;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
	/**
	 * Get all product variants by product
	 *
	 * @param  product
	 * @return
	 */
	List<ProductVariant> findAllByProduct(Product product);

	/**
	 * Get all product variants by product and active
	 *
	 * @param  product
	 * @param  active
	 * @return
	 */
	List<ProductVariant> findAllByProductAndActive(Product product, Boolean active);

	/**
	 * Get product variants by product and uom if exist
	 *
	 * @param  product
	 * @param  uom
	 * @return
	 */
	Optional<ProductVariant> findByProductAndUom(Product product, UOM uom);

	/**
	 * Get product variants by product, uom and id not if exist
	 *
	 * @param  product
	 * @param  uom
	 * @param  productvariantId
	 * @return
	 */
	Optional<ProductVariant> findByProductAndUomAndIdNot(Product product, UOM uom, Long productvariantId);

	/**
	 * Get product variants by sku if exist
	 *
	 * @param sku
	 */
	Optional<ProductVariant> findBySkuIgnoreCase(String sku);

	/**
	 * Get product variants by sku and id not if exist
	 *
	 * @param  sku
	 * @param  id
	 * @return
	 */
	Optional<ProductVariant> findBySkuIgnoreCaseAndIdNot(String sku, Long id);

}
