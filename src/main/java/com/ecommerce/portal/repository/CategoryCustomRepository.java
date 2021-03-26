/**
 *
 */
package com.ecommerce.portal.repository;

import java.util.List;

import com.ecommerce.portal.model.Category;

public interface CategoryCustomRepository {

	/**
	 * Get List of Category based on filter parameters with pagination
	 *
	 * @param  startIndex
	 * @param  pageSize
	 * @param  activeRecords
	 * @param  searchKeyword
	 * @return
	 */
	List<Category> getCategoryListBasedOnParams(Integer startIndex, Integer pageSize, Boolean activeRecords, String searchKeyword);

	/**
	 * Get count of Category based on filter parameters
	 *
	 * @param  activeRecords
	 * @param  searchKeyword
	 * @return
	 */
	Long getCategoryCountBasedOnParams(Boolean activeRecords, String searchKeyword);

}
