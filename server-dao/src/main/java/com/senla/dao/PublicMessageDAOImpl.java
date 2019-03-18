package com.senla.dao;

import com.senla.api.dao.PublicMessageDAO;
import com.senla.beans.PublicMessage;
import com.senla.beans.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class PublicMessageDAOImpl extends GenericDAOImpl<PublicMessage> implements PublicMessageDAO {

    public PublicMessageDAOImpl() {
        super(PublicMessage.class);
    }

    @Override
    public List<PublicMessage> getPublicMessagesByUserId(long id) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PublicMessage> criteriaQuery = criteriaBuilder.createQuery(PublicMessage.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.where(criteriaBuilder.equal(userRoot.get("id"), id));
        Join eventJoin = userRoot.join("publicMessages", JoinType.INNER);
        criteriaQuery.select(eventJoin);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
