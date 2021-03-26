package com.ecommerce.portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.portal.model.Category;

/**
 *
 * @author : ankit bhimani
 * @date   : Mar 26, 2021
 */
@Repository(value = "categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryCustomRepository {

	/**
	 * Get Category based on name for not given id
	 *
	 * @param  name
	 * @param  category
	 * @param  id
	 * @return
	 */
	Optional<Category> findByNameIgnoreCaseAndIdNot(String name, Long id);

	/**
	 * Get Category based on name
	 *
	 * @param  name
	 * @param  category
	 * @return
	 */
	Optional<Category> findByNameIgnoreCase(String name);
}
