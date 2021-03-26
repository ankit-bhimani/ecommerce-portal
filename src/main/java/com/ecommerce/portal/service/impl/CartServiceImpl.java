package com.ecommerce.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.portal.dto.CartDTO;
import com.ecommerce.portal.model.Cart;
import com.ecommerce.portal.model.Customer;
import com.ecommerce.portal.model.Product;
import com.ecommerce.portal.repository.CartRepository;
import com.ecommerce.portal.repository.CustomerRepository;
import com.ecommerce.portal.repository.ProductRepository;
import com.ecommerce.portal.service.CartService;

import javassist.NotFoundException;

@Service(value = "cartService")
@Transactional(rollbackFor = Throwable.class)
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void addCart(final CartDTO cartDTO) throws NotFoundException {
		Cart cart = new Cart();
		BeanUtils.copyProperties(cartDTO, cart);
		Customer customer = customerRepository.findById(cartDTO.getCustomerId())
				.orElseThrow(() -> new NotFoundException("customer not found for id ".concat(cartDTO.getCustomerId().toString())));

		Product product = productRepository.findById(cartDTO.getProductId())
				.orElseThrow(() -> new NotFoundException("product not found for id ".concat(cartDTO.getProductId().toString())));

		cart.setProduct(product);
		cart.setActive(true);
		cart.setCustomer(customer);
		cart.setVendor(product.getVendor());
		cartRepository.save(cart);
	}

	Cart getCartDetails(final Long cartId) throws NotFoundException {
		return cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("cart not found for id ".concat(cartId.toString())));
	}

	@Override
	public void deleteCartById(final Long cartId) throws NotFoundException {
		cartRepository.delete(getCartDetails(cartId));

	}

	@Override
	public List<CartDTO> getCustomerCartList(final Long customerId) throws NotFoundException {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new NotFoundException("customer not found for id ".concat(customerId.toString())));
		List<CartDTO> cartDTOs = new ArrayList<>();
		List<Cart> carts = cartRepository.findAllByCustomer(customer);
		for (Cart cart : carts) {
			cartDTOs.add(convertToDto(cart));
		}
		return cartDTOs;
	}

	CartDTO convertToDto(final Cart cart) {
		CartDTO cartDTO = new CartDTO();
		BeanUtils.copyProperties(cart, cartDTO);
		cartDTO.setCustomerId(cart.getCustomer().getId());
		cartDTO.setProductId(cart.getProduct().getId());
		cartDTO.setProductName(cart.getProduct().getName());
		cartDTO.setUomId(cart.getProduct().getUom().getId());
		cartDTO.setUomName(cart.getProduct().getUom().getUomLabel());
		return cartDTO;
	}

}
