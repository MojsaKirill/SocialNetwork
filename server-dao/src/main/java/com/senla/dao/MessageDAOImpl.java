package com.senla.dao;

import com.senla.api.dao.MessageDAO;
import com.senla.beans.Message;
import com.senla.beans.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class MessageDAOImpl extends GenericDAOImpl<Message> implements MessageDAO {

    public MessageDAOImpl() {
        super(Message.class);
    }

    @Override
    public List<Message> getMessagesByUserId(long id) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> criteriaQuery = criteriaBuilder.createQuery(Message.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.where(criteriaBuilder.equal(userRoot.get("id"), id));
        Join messageJoin = userRoot.join("messages", JoinType.INNER);
        criteriaQuery.select(messageJoin);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
