package com.ecommerce.portal.service;

import org.springframework.data.domain.Page;

import com.ecommerce.portal.exception.NotFoundException;
import com.ecommerce.portal.model.Role;

public interface RoleService {

	/**
	 * get role list
	 *
	 * @param  pageNumber
	 * @param  pageSize
	 * @param  activeRecords
	 * @param  isDefault
	 * @param  userType
	 * @return
	 */
	Page<Role> getRoleList(Integer pageNumber, Integer pageSize, Boolean activeRecords, final Boolean isDefault, final String userType);

	/**
	 * get role detail object by id
	 *
	 * @param  roleId
	 * @return
	 * @throws NotFoundException
	 */
	Role getRoleDetails(Long roleId) throws NotFoundException;

	/**
	 * @param  name
	 * @return
	 * @throws NotFoundException
	 */
	Role getRoleDetailByName(String name) throws NotFoundException;

}
