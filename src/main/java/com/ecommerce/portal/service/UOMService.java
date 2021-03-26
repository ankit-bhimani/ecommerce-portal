package com.ecommerce.portal.service;

import org.springframework.data.domain.Page;

import com.ecommerce.portal.dto.UOMDTO;
import com.ecommerce.portal.exception.NotFoundException;
import com.ecommerce.portal.exception.ValidationException;
import com.ecommerce.portal.model.UOM;

public interface UOMService {

	/**
	 * persist uom object
	 *
	 * @param  uomDTO
	 * @return
	 * @throws ValidationException
	 * @throws NotFoundException
	 */

	void addUOM(UOMDTO uomDTO) throws ValidationException, NotFoundException;

	/**
	 * update uom
	 *
	 * @param  uomDTO
	 * @return
	 * @throws ValidationException
	 * @throws NotFoundException
	 */
	void updateUOM(UOMDTO uomDTO) throws NotFoundException, ValidationException;

	/**
	 * get DTO object of uom
	 *
	 * @param  uomId
	 * @return
	 * @throws NotFoundException
	 */
	UOMDTO getUOM(Long uomId) throws NotFoundException;

	/**
	 * change status of uom (active/deActive)
	 *
	 * @param  uomId
	 * @param  isActive
	 * @throws NotFoundException
	 * @throws ValidationException
	 */
	void changeStatus(Long uomId, Boolean isActive) throws NotFoundException, ValidationException;

	/**
	 * check uom duplication and returning Boolean value.
	 *
	 * @param  uomDTO
	 * @return
	 * @throws ValidationException
	 * @throws NotFoundException
	 */
	Boolean isUOMExists(UOMDTO uomDTO);

	/**
	 * get detail object of uom
	 *
	 * @param  uomId
	 * @return
	 * @throws NotFoundException
	 */
	UOM getUOMDetail(Long uomId) throws NotFoundException;

	/**
	 * get list of uoms
	 *
	 * @param  pageNumber
	 * @param  pageSize
	 * @param  activeRecords
	 * @return
	 * @throws NotFoundException
	 */
	Page<UOM> getUOMList(Integer pageNumber, Integer pageSize, Boolean activeRecords) throws NotFoundException;

}
