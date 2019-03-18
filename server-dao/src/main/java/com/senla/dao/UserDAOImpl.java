package com.senla.dao;

import com.senla.api.dao.UserDAO;
import com.senla.beans.User;
import com.senla.beans.UserInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public User getUserByLogin(String login) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        userRoot.fetch("roles");
        criteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get("login"), login));
        List<User> users = entityManager.createQuery(criteriaQuery).getResultList();
        if (users.size() != 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public List<User> getUsersByFullName(String lastName, String firstName) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        Join<User, UserInfo> userInfoJoin = userRoot.join("userInfo");
        criteriaQuery.select(userRoot).where(criteriaBuilder.equal(userInfoJoin.get("lastName"), lastName),
                criteriaBuilder.equal(userInfoJoin.get("firstName"), firstName));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<User> getFriendsByUserId(long id) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.where(criteriaBuilder.equal(userRoot.get("id"), id));
        Join friendsJoin = userRoot.join("friends", JoinType.INNER);
        criteriaQuery.select(friendsJoin);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
