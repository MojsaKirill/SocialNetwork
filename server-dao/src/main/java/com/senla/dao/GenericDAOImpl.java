package com.senla.dao;

import com.senla.api.dao.GenericDao;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class GenericDAOImpl<T> implements GenericDao<T> {
    @PersistenceContext
    private EntityManager entityManager;

    private Class<?> entityClass;

    public GenericDAOImpl(Class entityClass) {
        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public T getById(long id) {
        return (T) entityManager.find(entityClass, id);
    }

    @Override
    public List<T> getAll() {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = (CriteriaQuery<T>) criteriaBuilder.createQuery(entityClass);
        Root<T> root = (Root<T>) criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
        Query<T> query = (Query<T>) entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void persist(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public T merge(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }
}
