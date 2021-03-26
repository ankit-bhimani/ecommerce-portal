package com.ecommerce.portal.service;

import com.ecommerce.portal.dto.AddStockDto;
import com.ecommerce.portal.dto.StockDetailsDTO;
import com.ecommerce.portal.dto.StockTransferDto;
import com.ecommerce.portal.exception.NotFoundException;
import com.ecommerce.portal.exception.ValidationException;
import com.ecommerce.portal.model.ProductVariant;
import com.ecommerce.portal.model.StockDetails;

public interface StockDetailsService {

	/**
	 * @param  addStockDto
	 * @throws ValidationException
	 * @throws NotFoundException
	 */
	void addStockDetails(AddStockDto addStockDto) throws ValidationException, NotFoundException;

	/**
	 * @param  productvariant
	 * @return
	 */
	StockDetails getStockDetailsForProductVariant(ProductVariant productvariant);

	/**
	 * @param  stockTransferDto
	 * @return
	 * @throws NotFoundException
	 * @throws ValidationException
	 */
	StockDetails updateStockDetails(StockTransferDto stockTransferDto) throws NotFoundException, ValidationException;

	/**
	 * @param  stockDetailsId
	 * @return
	 * @throws NotFoundException
	 */
	StockDetailsDTO getStockDetails(Long stockDetailsId) throws NotFoundException;

	/**
	 * @param  stockDetailsId
	 * @return
	 * @throws NotFoundException
	 */
	StockDetails getStockDetailsDetails(Long stockDetailsId) throws NotFoundException;

}
