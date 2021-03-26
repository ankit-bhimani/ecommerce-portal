package com.ecommerce.portal.service;

import java.util.List;

import com.ecommerce.portal.dto.CartDTO;

import javassist.NotFoundException;

public interface CartService {

	void addCart(CartDTO cartDTO) throws NotFoundException;

	void deleteCartById(Long cartId) throws NotFoundException;

	List<CartDTO> getCustomerCartList(Long customerId) throws NotFoundException;
}
