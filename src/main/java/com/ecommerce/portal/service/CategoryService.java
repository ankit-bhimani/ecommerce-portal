package com.ecommerce.portal.service;

import java.util.List;

import com.ecommerce.portal.dto.CategoryDTO;
import com.ecommerce.portal.exception.NotFoundException;
import com.ecommerce.portal.exception.ValidationException;
import com.ecommerce.portal.model.Category;

public interface CategoryService {

	/**
	 * Add category
	 *
	 * @param  categoryDTO
	 * @throws ValidationException
	 * @throws NotFoundException
	 */
	void addCategory(CategoryDTO categoryDTO) throws ValidationException, NotFoundException;

	/**
	 * Update category
	 *
	 * @param  categoryDTO
	 * @throws ValidationException
	 * @throws NotFoundException
	 */
	void updateCategory(CategoryDTO categoryDTO) throws ValidationException, NotFoundException;

	/**
	 * Get category details based on categoryId
	 *
	 * @param  categoryId
	 * @return
	 * @throws NotFoundException
	 */
	CategoryDTO getCategory(final Long categoryId) throws NotFoundException;

	/**
	 * Get Category details based on Id : Specially for internally calls
	 *
	 * @param  categoryId
	 * @return
	 * @throws NotFoundException
	 */
	Category getCategoryDetails(Long categoryId) throws NotFoundException;

	/**
	 * Check category is exists with same name
	 *
	 * @param  categoryDTO
	 * @return
	 */
	boolean isCategoryExists(CategoryDTO categoryDTO);

	/**
	 * Change status of category (active/deActive)
	 *
	 * @param  categoryId
	 * @param  active
	 * @throws ValidationException
	 * @throws NotFoundException
	 * @throws ParseException
	 */
	void changeStatus(Long categoryId, Boolean active) throws ValidationException, NotFoundException;

	/**
	 * Get category list based on parameters
	 *
	 * @param  startIndex
	 * @param  pageSize
	 * @param  storeId
	 * @param  activeRecords
	 * @param  searchKeyword
	 * @return
	 */
	List<Category> getCategoryListBasedOnParams(Integer startIndex, Integer pageSize, Boolean activeRecords, String searchKeyword);

	/**
	 * get category count based on params
	 *
	 * @param  activeRecords
	 * @param  searchKeyword
	 * @return
	 */
	Long getCategoryListCountBasedOnParams(Boolean activeRecords, String searchKeyword);

}
