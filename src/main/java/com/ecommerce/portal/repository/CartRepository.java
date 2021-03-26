package com.ecommerce.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.portal.model.Cart;
import com.ecommerce.portal.model.Customer;

/**
 *
 * @author :ankit bhimani
 * @date   : Mar 27, 2021
 */
@Repository(value = "cartRepository")
public interface CartRepository extends JpaRepository<Cart, Long> {

	List<Cart> findAllByCustomer(Customer customer);
}
