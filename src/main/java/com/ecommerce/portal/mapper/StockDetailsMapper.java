package com.ecommerce.portal.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ecommerce.portal.dto.StockDetailsDTO;
import com.ecommerce.portal.dto.StockResponseDTO;
import com.ecommerce.portal.model.Product;
import com.ecommerce.portal.model.StockDetails;

/**
 *
 * @author :ankit bhimani
 * @date   : Mar 27, 2021
 */
@Component
public class StockDetailsMapper {

	public StockResponseDTO toResponseDto(final StockDetails stockDetails) {
		StockResponseDTO stockResponseDTO = new StockResponseDTO();
		BeanUtils.copyProperties(stockDetails, stockResponseDTO);
		Product product = stockDetails.getProduct();
		stockResponseDTO.setProductId(product.getId());
		stockResponseDTO.setProductName(product.getName());
		stockResponseDTO.setUomId(product.getUom().getId());
		stockResponseDTO.setUomLabel(product.getUom().getUomLabel());
		return stockResponseDTO;
	}

	public StockDetails toEntity(final StockDetailsDTO stockDetailsDTO) {
		StockDetails stockDetails = new StockDetails();
		BeanUtils.copyProperties(stockDetailsDTO, stockDetails);
		stockDetails.setAvailable(stockDetailsDTO.getAvailable());
		stockDetails.setReserved(0D);
		stockDetails.setDelivered(0D);
		stockDetails.setActive(true);
		return stockDetails;
	}

	public List<StockResponseDTO> toResponseDtos(final List<StockDetails> stockDetailsList) {
		List<StockResponseDTO> results = new ArrayList<>();
		for (StockDetails stockDetails : stockDetailsList) {
			results.add(toResponseDto(stockDetails));
		}
		return results;
	}
}
