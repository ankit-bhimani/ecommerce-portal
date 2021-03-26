/**
 *
 */
package com.ecommerce.portal.service;

import java.util.List;

import com.ecommerce.portal.dto.ProductRequestDTO;
import com.ecommerce.portal.dto.ProductResponseDTO;
import com.ecommerce.portal.exception.NotFoundException;
import com.ecommerce.portal.exception.ValidationException;
import com.ecommerce.portal.model.Product;

public interface ProductService {

	/**
	 *
	 * @param  productRequestDTO
	 * @return
	 * @throws NotFoundException
	 * @throws ValidationException
	 */
	ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) throws NotFoundException, ValidationException;

	/**
	 * @param  productId
	 * @return
	 * @throws NotFoundException
	 * @throws ValidationException
	 */
	ProductResponseDTO getProduct(Long productId) throws NotFoundException, ValidationException;

	/**
	 * @param  productId
	 * @throws NotFoundException
	 */
	Product getProductDetails(Long productId) throws NotFoundException;

	/**
	 * Change status
	 *
	 * @param  productId
	 * @param  active
	 * @return
	 * @throws NotFoundException
	 * @throws ValidationException
	 */
	ProductResponseDTO changeStatus(Long productId, Boolean active) throws NotFoundException, ValidationException;

	/**
	 * Update product
	 *
	 * @param  productRequestDTO
	 * @return
	 * @throws NotFoundException
	 * @throws ValidationException
	 */
	ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO) throws NotFoundException, ValidationException;

	/**
	 * @param  activeRecords
	 * @param  searchKeyword
	 * @param  pageNumber
	 * @param  pageSize
	 * @return
	 * @throws NotFoundException
	 * @throws ValidationException
	 */
	List<ProductResponseDTO> getProductListBasedOnParams(Boolean activeRecords, String searchKeyword, Integer pageNumber, Integer pageSize)
			throws NotFoundException, ValidationException;

	/**
	 * @param  productRequestDTO
	 * @return
	 */
	boolean checkIfProductExists(ProductRequestDTO productRequestDTO);

	/**
	 * @param  categoryId
	 * @param  b
	 * @return
	 */
	List<Product> getProductByCategoryAndActive(Long categoryId, boolean b);
}
