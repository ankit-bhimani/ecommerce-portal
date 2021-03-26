package com.ecommerce.portal.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ecommerce.portal.dto.CategoryDTO;
import com.ecommerce.portal.model.Category;

/**
 *
 * @author : ankit bhimani
 * @date   : Mar 26, 2021
 */
@Component
public class CategoryMapper {

	public CategoryDTO toDto(final Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		BeanUtils.copyProperties(category, categoryDTO);
		return categoryDTO;
	}

	public Category toEntity(final CategoryDTO categoryDTO) {
		Category category = new Category();
		BeanUtils.copyProperties(categoryDTO, category);
		return category;
	}

	public List<CategoryDTO> toDtos(final List<Category> categoryList) {
		List<CategoryDTO> results = new ArrayList<>();
		for (Category category : categoryList) {
			results.add(toDto(category));
		}
		return results;
	}
}
