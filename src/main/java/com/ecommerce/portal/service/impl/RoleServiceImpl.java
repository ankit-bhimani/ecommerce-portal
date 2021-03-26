package com.ecommerce.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.portal.exception.NotFoundException;
import com.ecommerce.portal.locale.MessageByLocaleService;
import com.ecommerce.portal.model.Role;
import com.ecommerce.portal.repository.RoleRepository;
import com.ecommerce.portal.service.RoleService;

@Service
@Transactional(rollbackFor = Throwable.class)
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private MessageByLocaleService messageByLocaleService;

	@Override
	public Role getRoleDetails(final Long roleId) throws NotFoundException {
		return roleRepository.findById(roleId)
				.orElseThrow(() -> new NotFoundException(messageByLocaleService.getMessage("role.not.found", new Object[] { roleId })));
	}

	@Override
	public Page<Role> getRoleList(final Integer pageNumber, final Integer pageSize, final Boolean activeRecords, final Boolean isDefault,
			final String userType) {
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("name"));
		if (isDefault != null) {
			if (activeRecords != null) {
				if (userType != null) {
					return roleRepository.findAllByActiveAndIsDefaultAndUserType(activeRecords, isDefault, userType, pageable);
				} else {
					return roleRepository.findAllByActiveAndIsDefault(activeRecords, isDefault, pageable);
				}
			} else {
				if (userType != null) {
					return roleRepository.findAllByIsDefaultAndUserType(isDefault, userType, pageable);
				} else {
					return roleRepository.findAllByIsDefault(isDefault, pageable);
				}
			}
		} else {
			return getRoleList(activeRecords, userType, pageable);
		}
	}

	/**
	 * @param  activeRecords
	 * @param  userType
	 * @param  pageable
	 * @return
	 */
	private Page<Role> getRoleList(final Boolean activeRecords, final String userType, final Pageable pageable) {
		if (activeRecords != null) {
			if (userType != null) {
				return roleRepository.findAllByActiveAndUserType(activeRecords, userType, pageable);
			} else {
				return roleRepository.findAllByActive(activeRecords, pageable);
			}
		} else {
			if (userType != null) {
				return roleRepository.findAllByUserType(userType, pageable);
			} else {
				return roleRepository.findAll(pageable);
			}
		}
	}

	@Override
	public Role getRoleDetailByName(final String name) throws NotFoundException {
		return roleRepository.findByNameIgnoreCase(name)
				.orElseThrow(() -> new NotFoundException(messageByLocaleService.getMessage("role.not.found.name", new Object[] { name })));
	}

}
