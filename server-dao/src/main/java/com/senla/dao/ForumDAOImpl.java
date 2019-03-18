package com.senla.dao;

import com.senla.api.dao.ForumDAO;
import com.senla.beans.Forum;
import com.senla.beans.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class ForumDAOImpl extends GenericDAOImpl<Forum> implements ForumDAO {
    public ForumDAOImpl() {
        super(Forum.class);
    }

    @Override
    public List<Forum> getForumsByUserId(long id) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Forum> criteriaQuery = criteriaBuilder.createQuery(Forum.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.where(criteriaBuilder.equal(userRoot.get("id"), id));
        Join forumJoin = userRoot.join("forums", JoinType.INNER);
        criteriaQuery.select(forumJoin);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<User> getForumSubscribersById(long id) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<Forum> forumRoot = criteriaQuery.from(Forum.class);
        criteriaQuery.where(criteriaBuilder.equal(forumRoot.get("id"), id));
        Join userJoin = forumRoot.join("subscribers", JoinType.INNER);
        criteriaQuery.select(userJoin);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public User getModeratorByForumId(long id) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<Forum> forumRoot = criteriaQuery.from(Forum.class);
        criteriaQuery.where(criteriaBuilder.equal(forumRoot.get("id"), id));
        Join userJoin = forumRoot.join("moderator", JoinType.INNER);
        criteriaQuery.select(userJoin);
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public List<Forum> getForumsByName(String name) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Forum> criteriaQuery = criteriaBuilder.createQuery(Forum.class);
        Root<Forum> forumRoot = criteriaQuery.from(Forum.class);
        criteriaQuery.select(forumRoot).where(criteriaBuilder.equal(forumRoot.get("name"), name));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
