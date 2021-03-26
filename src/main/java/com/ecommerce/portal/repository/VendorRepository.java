package com.ecommerce.portal.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.portal.model.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

	/**
	 * Get vendor by email and id not if exist
	 *
	 * @param  email
	 * @param  vendorId
	 * @return
	 */
	Optional<Vendor> findByEmailAndIdNot(String email, Long vendorId);

	/**
	 * Get vendor by email if exist
	 *
	 * @param  email
	 * @return
	 */
	Optional<Vendor> findByEmail(String email);

	/**
	 * Get vendor page by search keyword
	 *
	 * @param  searchKeyword
	 * @param  pageable
	 * @return
	 */
	Page<Vendor> findAllByNameContainingIgnoreCase(String searchKeyword, Pageable pageable);
}
