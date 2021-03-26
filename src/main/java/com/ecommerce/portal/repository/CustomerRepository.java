package com.ecommerce.portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.portal.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	/**
	 * Get Customer by email if exist
	 *
	 * @param  email
	 * @return
	 */
	Optional<Customer> findByEmail(String email);

	/**
	 * Get Customer by email and id not equal if exist
	 *
	 * @param  email
	 * @param  id
	 * @return
	 */

	Optional<Customer> findByEmailAndIdNot(String email, Long id);

	/**
	 * Get customer page by active
	 *
	 * @param  activeRecords
	 * @param  pageable
	 * @return
	 */
	Page<Customer> findAllByActive(Boolean activeRecords, Pageable pageable);

	/**
	 * Get customer list by active
	 *
	 * @param  active
	 * @return
	 */
	List<Customer> findAllByActive(Boolean active);

	/**
	 * Get customer page by active and search keyword
	 *
	 * @param  active
	 * @param  searchKeyword
	 * @param  pageable
	 * @return
	 */
	Page<Customer> findAllByActiveAndNameContainingIgnoreCase(Boolean active, String searchKeyword, Pageable pageable);

	/**
	 * Get customer page by search keyword
	 *
	 * @param  searchKeyword
	 * @param  pageable
	 * @return
	 */
	Page<Customer> findAllByNameContainingIgnoreCase(String searchKeyword, Pageable pageable);

}
