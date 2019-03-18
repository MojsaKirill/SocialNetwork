package com.senla.api.dao;

import java.util.List;

public interface GenericDao<T> {
	T getById(long id);

	List<T> getAll();

	void persist(T entity);

	T merge(T entity);

	void delete(T entity);
}
