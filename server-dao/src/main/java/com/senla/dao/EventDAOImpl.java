package com.senla.dao;

import com.senla.api.dao.EventDAO;
import com.senla.beans.Event;
import com.senla.beans.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class EventDAOImpl extends GenericDAOImpl<Event> implements EventDAO {
    public EventDAOImpl() {
        super(Event.class);
    }

    @Override
    public List<Event> getEventsByUserId(long id) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Event> criteriaQuery = criteriaBuilder.createQuery(Event.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.where(criteriaBuilder.equal(userRoot.get("id"), id));
        Join eventJoin = userRoot.join("events", JoinType.INNER);
        criteriaQuery.select(eventJoin);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<User> getEventSubscribersById(long id) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<Event> eventRoot = criteriaQuery.from(Event.class);
        criteriaQuery.where(criteriaBuilder.equal(eventRoot.get("id"), id));
        Join userJoin = eventRoot.join("subscribers", JoinType.INNER);
        criteriaQuery.select(userJoin);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public User getModeratorByEventId(long id) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<Event> eventRoot = criteriaQuery.from(Event.class);
        criteriaQuery.where(criteriaBuilder.equal(eventRoot.get("id"), id));
        Join userJoin = eventRoot.join("moderator", JoinType.INNER);
        criteriaQuery.select(userJoin);
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public List<Event> getEventsByName(String name) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Event> criteriaQuery = criteriaBuilder.createQuery(Event.class);
        Root<Event> eventRoot = criteriaQuery.from(Event.class);
        criteriaQuery.select(eventRoot).where(criteriaBuilder.equal(eventRoot.get("name"), name));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
