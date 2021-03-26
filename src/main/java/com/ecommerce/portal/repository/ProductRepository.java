/**
 *
 */
package com.ecommerce.portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.portal.model.Product;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {

	/**
	 * @param  vendorId
	 * @param  name
	 * @param  id
	 * @return
	 */
	Optional<Product> findByVendorIdAndNameAndIdNot(Long vendorId, String name, Long id);

	/**
	 * @param  vendorId
	 * @param  name
	 * @return
	 */
	Optional<Product> findByVendorIdAndName(Long vendorId, String name);

	/**
	 * @param  activeRecords
	 * @param  searchKeyword
	 * @param  pageable
	 * @return
	 */
	Page<Product> findAllByActiveAndNameContainingIgnoreCase(Boolean activeRecords, String searchKeyword, Pageable pageable);

	/**
	 * @param  activeRecords
	 * @param  pageable
	 * @return
	 */
	Page<Product> findAllByActive(Boolean activeRecords, Pageable pageable);

	/**
	 * @param  searchKeyword
	 * @param  pageable
	 * @return
	 */
	Page<Product> findAllByNameContainingIgnoreCase(String searchKeyword, Pageable pageable);

	/**
	 * @param  categoryId
	 * @param  b
	 * @return
	 */
	List<Product> findAllByCategoryIdAndActive(Long categoryId, boolean b);

}
