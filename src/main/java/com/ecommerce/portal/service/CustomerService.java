package com.ecommerce.portal.service;

import org.springframework.data.domain.Page;

import com.ecommerce.portal.dto.CustomerRequestDTO;
import com.ecommerce.portal.dto.CustomerResponseDTO;
import com.ecommerce.portal.exception.NotFoundException;
import com.ecommerce.portal.exception.ValidationException;
import com.ecommerce.portal.model.Customer;

public interface CustomerService {

	/**
	 * Add customer
	 *
	 * @param  customerRequestDTO
	 * @param  isAuthorized
	 * @return
	 * @throws ValidationException
	 * @throws NotFoundException
	 */
	CustomerResponseDTO addCustomer(CustomerRequestDTO customerRequestDTO) throws ValidationException, NotFoundException;

	/**
	 * Update customer
	 *
	 * @param  customerRequestDTO
	 * @return
	 * @throws NotFoundException
	 * @throws ValidationException
	 */
	CustomerResponseDTO updateCustomer(CustomerRequestDTO customerRequestDTO) throws NotFoundException, ValidationException;

	/**
	 * Get customer detail by id
	 *
	 * @param  customerId
	 * @return
	 * @throws NotFoundException
	 * @throws ValidationException
	 */
	CustomerResponseDTO getCustomer(Long customerId) throws NotFoundException, ValidationException;

	/**
	 * Get customer list based on parameters
	 *
	 * @param  pageNumber
	 * @param  pageSize
	 * @param  active
	 * @param  searchKeyword
	 *
	 * @return
	 * @throws NotFoundException
	 * @throws ValidationException
	 */
	Page<Customer> getCustomerList(Integer pageNumber, Integer pageSize, Boolean active, String searchKeyword) throws NotFoundException, ValidationException;

	/**
	 * Change status of customer (active/deActive)
	 *
	 * @param  customerId
	 * @param  active
	 * @throws NotFoundException
	 * @throws ValidationException
	 */
	Customer changeStatus(Long customerId, Boolean active) throws NotFoundException, ValidationException;

	/**
	 * Check whether customer is exists with same contact number or not
	 *
	 * @param  customerRequestDTO
	 * @return
	 * @throws ValidationException
	 */
	boolean isExists(CustomerRequestDTO customerRequestDTO) throws ValidationException;

	/**
	 * Check whether customer is exists with same email or not
	 *
	 * @param  customerRequestDTO
	 * @return
	 * @throws ValidationException
	 */
	boolean isEmailExists(CustomerRequestDTO customerRequestDTO) throws ValidationException;

	/**
	 * Get customer details based on customerId : Specially for internal calls
	 *
	 * @param  customerId
	 * @return
	 * @throws NotFoundException
	 */
	Customer getCustomerDetails(Long customerId) throws NotFoundException;

}