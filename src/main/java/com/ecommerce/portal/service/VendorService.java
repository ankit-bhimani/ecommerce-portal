package com.ecommerce.portal.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.portal.dto.VendorRequestDTO;
import com.ecommerce.portal.dto.VendorResponseDTO;
import com.ecommerce.portal.exception.NotFoundException;
import com.ecommerce.portal.exception.ValidationException;
import com.ecommerce.portal.model.Vendor;

/**
 * @author : Kody Technolab Pvt. Ltd.
 * @date   : Jun 25, 2020
 */
public interface VendorService {
	/**
	 * Add vendor
	 *
	 * @param  vendorRequestDTO
	 * @param  storeImages
	 * @return
	 * @throws ValidationException
	 * @throws NotFoundException
	 * @throws FileOperationException
	 */
	VendorResponseDTO addVendor(VendorRequestDTO vendorRequestDTO, Map<String, MultipartFile> storeImages) throws ValidationException, NotFoundException;

	/**
	 * update vendor's personal details
	 *
	 * @param  vendorRequestDTO
	 * @return
	 * @throws NotFoundException
	 * @throws ValidationException
	 */
	void updatePersonalDetails(VendorRequestDTO vendorRequestDTO) throws NotFoundException, ValidationException;

	/**
	 * Get detail object of vendor
	 *
	 * @param  vendorId
	 * @return
	 * @throws NotFoundException
	 */
	Vendor getVendorDetails(Long vendorId) throws NotFoundException;

	/**
	 * Change status of vendor
	 *
	 * @param  vendorId
	 * @param  newStatus
	 * @return
	 * @throws NotFoundException
	 * @throws ValidationException
	 * @throws ParseException
	 */
	Vendor changeVendorStatus(Long vendorId, String newStatus) throws NotFoundException, ValidationException;

	/**
	 * Get vendor page based on parameters
	 *
	 * @param  pageNumber
	 * @param  pageSize
	 * @param  status
	 * @param  searchKeyword
	 * @return
	 */
	Page<Vendor> getVendorList(Integer pageNumber, Integer pageSize, String status, final String searchKeyword);

	/**
	 * Get vendor by id
	 *
	 * @param  vendorId
	 * @return
	 * @throws NotFoundException
	 */
	VendorResponseDTO getVendor(Long vendorId) throws NotFoundException;
}
