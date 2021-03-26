package com.ecommerce.portal.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.portal.dto.CategoryDTO;
import com.ecommerce.portal.exception.NotFoundException;
import com.ecommerce.portal.exception.ValidationException;
import com.ecommerce.portal.locale.MessageByLocaleService;
import com.ecommerce.portal.mapper.CategoryMapper;
import com.ecommerce.portal.model.Category;
import com.ecommerce.portal.model.Product;
import com.ecommerce.portal.repository.CategoryRepository;
import com.ecommerce.portal.service.CategoryService;
import com.ecommerce.portal.service.ProductService;

@Service(value = "categoryService")
@Transactional(rollbackFor = Throwable.class)
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private MessageByLocaleService messageByLocaleService;

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private ProductService productService;

	@Override
	public void addCategory(final CategoryDTO categoryDTO) throws ValidationException, NotFoundException {
		Category category = categoryMapper.toEntity(categoryDTO);
		category.setActive(true);
		categoryRepository.save(category);
	}

	@Override
	public void updateCategory(final CategoryDTO categoryDTO) throws ValidationException, NotFoundException {
		if (categoryDTO.getId() == null) {
			throw new ValidationException(messageByLocaleService.getMessage("category.id.not.null", null));
		}
		/**
		 * Check whether category is exists or not.
		 */
		Category existingCategory = getCategoryDetails(categoryDTO.getId());
		Category category = categoryMapper.toEntity(categoryDTO);
		category.setActive(existingCategory.getActive());
		categoryRepository.save(category);
	}

	@Override
	public CategoryDTO getCategory(final Long categoryId) throws NotFoundException {
		return categoryMapper.toDto(getCategoryDetails(categoryId));
	}

	@Override
	public Category getCategoryDetails(final Long categoryId) throws NotFoundException {
		return categoryRepository.findById(categoryId)
				.orElseThrow(() -> new NotFoundException(messageByLocaleService.getMessage("category.not.found", new Object[] { categoryId })));
	}

	@Override
	public boolean isCategoryExists(final CategoryDTO categoryDTO) {
		if (categoryDTO.getId() != null) {
			/**
			 * At the time of update is category with same name exist or not except it's own ID
			 */
			return categoryRepository.findByNameIgnoreCaseAndIdNot(categoryDTO.getName(), categoryDTO.getId()).isPresent();
		} else {
			/**
			 * At the time of update is category with same name exist or not
			 */
			return categoryRepository.findByNameIgnoreCase(categoryDTO.getName()).isPresent();
		}
	}

	@Override
	public void changeStatus(final Long categoryId, final Boolean active) throws ValidationException, NotFoundException {
		Category existingCategory = getCategoryDetails(categoryId);
		LOGGER.info("Existing category details {} ", existingCategory);
		if (active == null) {
			throw new ValidationException(messageByLocaleService.getMessage("active.not.null", null));
		} else if (existingCategory.getActive().equals(active)) {
			throw new ValidationException(messageByLocaleService.getMessage(Boolean.TRUE.equals(active) ? "category.active" : "category.deactive", null));
		} else {
			if (Boolean.FALSE.equals(active)) {
				/**
				 * DeActive Product for this subCategory
				 */
				List<Product> productList = productService.getProductByCategoryAndActive(existingCategory.getId(), true);
				if (productList != null) {
					for (Product product : productList) {
						productService.changeStatus(product.getId(), false);
					}
				}
			}
			existingCategory.setActive(active);
			categoryRepository.save(existingCategory);
		}
	}

	@Override
	public List<Category> getCategoryListBasedOnParams(final Integer startIndex, final Integer pageSize, final Boolean activeRecords,
			final String searchKeyword) {
		return categoryRepository.getCategoryListBasedOnParams(startIndex, pageSize, activeRecords, searchKeyword);
	}

	@Override
	public Long getCategoryListCountBasedOnParams(final Boolean activeRecords, final String searchKeyword) {
		return categoryRepository.getCategoryCountBasedOnParams(activeRecords, searchKeyword);
	}

}
