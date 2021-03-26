/**
 *
 */
package com.ecommerce.portal.repository;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ecommerce.portal.model.Category;

@Repository(value = "categoryCustomRepository")
public class CategoryCustomRepositoryImpl implements CategoryCustomRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@SuppressWarnings("unchecked")
	public List<Category> getCategoryListBasedOnParams(final Integer startIndex, final Integer pageSize, final Boolean activeRecords,
			final String searchKeyword) {
		Map<String, Object> paramMap = new HashMap<>();
		StringBuilder sqlQuery = new StringBuilder("select c.* from category c ");

		sqlQuery.append(" where 1=1 ");
		addConditions(activeRecords, searchKeyword, sqlQuery, paramMap);
		sqlQuery.append(" group by (c.id)");

		if (startIndex != null && pageSize != null) {
			sqlQuery.append(" offset :startIndex  limit :pageSize ");
			paramMap.put("startIndex", startIndex);
			paramMap.put("pageSize", pageSize);

		}

		Query q = entityManager.createNativeQuery(sqlQuery.toString(), "CategoryMapping");
		paramMap.entrySet().forEach(p -> q.setParameter(p.getKey(), p.getValue()));
		return q.getResultList();
	}

	private void addConditions(final Boolean activeRecords, final String searchKeyword, final StringBuilder sqlQuery, final Map<String, Object> paramMap) {
		if (activeRecords != null) {
			sqlQuery.append(" and c.active = :activeRecords ");
			paramMap.put("activeRecords", activeRecords);
		}

		if (searchKeyword != null) {
			sqlQuery.append(" and( lower(c.name) like :searchKeyword )");
			paramMap.put("searchKeyword", "%" + searchKeyword.toLowerCase() + "%");
		}

	}

	@Override
	public Long getCategoryCountBasedOnParams(final Boolean activeRecords, final String searchKeyword) {
		Map<String, Object> paramMap = new HashMap<>();
		StringBuilder sqlQuery = new StringBuilder("select count(total) from (select count(c.*) from category c ");

		sqlQuery.append(" where 1=1 ");
		addConditions(activeRecords, searchKeyword, sqlQuery, paramMap);
		sqlQuery.append(" group by (c.id)) as total ");
		Query q = entityManager.createNativeQuery(sqlQuery.toString());
		paramMap.entrySet().forEach(p -> q.setParameter(p.getKey(), p.getValue()));
		return ((BigInteger) q.getSingleResult()).longValue();
	}

}
