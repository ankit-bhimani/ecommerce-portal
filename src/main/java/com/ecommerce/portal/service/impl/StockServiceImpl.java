package com.ecommerce.portal.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.portal.constant.Constant;
import com.ecommerce.portal.dto.StockDetailsDTO;
import com.ecommerce.portal.dto.StockTransferDto;
import com.ecommerce.portal.mapper.StockDetailsMapper;
import com.ecommerce.portal.model.Product;
import com.ecommerce.portal.model.StockDetails;
import com.ecommerce.portal.repository.ProductRepository;
import com.ecommerce.portal.repository.StockDetailsRepository;
import com.ecommerce.portal.service.StockService;

import javassist.NotFoundException;

@Service(value = "stockService")
@Transactional(rollbackFor = Throwable.class)
public class StockServiceImpl implements StockService {

	@Autowired
	private StockDetailsRepository stockDetailsRepository;

	@Autowired
	private StockDetailsMapper stockDetailsMapper;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void addStock(final StockDetailsDTO stockDetailsDTO) {
		StockDetails stockDetails = stockDetailsMapper.toEntity(stockDetailsDTO);
		Optional<Product> optProduct = productRepository.findById(stockDetailsDTO.getProductId());
		if (optProduct.isPresent()) {
			stockDetails.setProduct(optProduct.get());
			stockDetailsRepository.save(stockDetails);
		}
	}

	@Override
	public StockDetails getStockDetailByProduct(final Long productId) throws NotFoundException {
		Optional<Product> optProduct = productRepository.findById(productId);
		if (optProduct.isPresent()) {
			return stockDetailsRepository.findByProduct(optProduct.get())
					.orElseThrow(() -> new NotFoundException("Stock not found for product ".concat(productId.toString())));
		} else {
			throw new NotFoundException("Product not found for product ".concat(productId.toString()));
		}
	}

	@Override
	public void transferStock(final StockTransferDto stockTransferDto) throws NotFoundException {
		StockDetails stockDetails = getStockDetailByProduct(stockTransferDto.getProductId());
		if (Constant.AVAILABLE.equals(stockTransferDto.getTransferedTo())) {
			stockDetails.setAvailable(stockDetails.getAvailable() + stockTransferDto.getQuantity());
		} else if (Constant.RESERVED.equals(stockTransferDto.getTransferedTo())) {
			stockDetails.setReserved(stockDetails.getReserved() + stockTransferDto.getQuantity());
		} else if (Constant.DELIVERED.equals(stockTransferDto.getTransferedTo())) {
			stockDetails.setDelivered(stockDetails.getDelivered() + stockTransferDto.getQuantity());
		} else {
			throw new NotFoundException("stock to not found" + stockTransferDto.getTransferedTo());
		}
		if (Constant.AVAILABLE.equals(stockTransferDto.getTransferedFrom())) {
			Double availableQty = stockDetails.getAvailable() - stockTransferDto.getQuantity();
			stockDetails.setAvailable(availableQty);
			if (availableQty < 0.0d) {
				throw new NotFoundException("insufficient stock");
			}
		} else if (Constant.RESERVED.equals(stockTransferDto.getTransferedFrom())) {
			Double availableQty = stockDetails.getReserved() - stockTransferDto.getQuantity();
			stockDetails.setReserved(availableQty);
			if (availableQty < 0.0d) {
				throw new NotFoundException("insufficient stock");
			}
		}
		stockDetailsRepository.save(stockDetails);
	}

}
