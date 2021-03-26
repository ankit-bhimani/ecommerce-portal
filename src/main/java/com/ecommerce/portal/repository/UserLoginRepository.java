package com.ecommerce.portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.portal.model.Role;
import com.ecommerce.portal.model.UserLogin;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {

	/**
	 * @param  email
	 * @param  upperCase
	 * @return
	 */
	Optional<UserLogin> findByEmailAndEntityType(String email, String entityType);

	/**
	 * @param  email
	 * @param  name
	 * @return
	 */
	Optional<UserLogin> findByEmailAndEntityTypeIsNull(String email);

	/**
	 * Get user list based on role if exist
	 *
	 * @param  actualUser
	 * @param  role
	 * @return
	 */
	Optional<List<UserLogin>> findAllByRole(Role role);

	/**
	 * get User login based on email and role
	 *
	 * @param  email
	 * @param  name
	 * @return
	 */
	Optional<UserLogin> findByEmailAndRole(String email, Role role);

	/**
	 * Get user login based on email, entity type and entityId not : used for update user validation.
	 *
	 * @param  email
	 * @param  entityType
	 * @param  entityId
	 * @return
	 */
	Optional<UserLogin> findByEmailAndEntityTypeAndEntityIdNot(String email, String entityType, Long entityId);

}
