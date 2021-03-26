package com.ecommerce.portal.service;

import com.ecommerce.portal.dto.StockDetailsDTO;
import com.ecommerce.portal.dto.StockTransferDto;
import com.ecommerce.portal.model.StockDetails;

import javassist.NotFoundException;

/**
 *
 * @author :ankit bhimani
 * @date   : Mar 27, 2021
 */
public interface StockService {

	void addStock(StockDetailsDTO stockDetailsDTO);

	void transferStock(StockTransferDto stockTransferDto) throws NotFoundException;

	StockDetails getStockDetailByProduct(Long productId) throws NotFoundException;
}
