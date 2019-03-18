package com.senla.dao;

import com.senla.api.dao.ForumMessageDAO;
import com.senla.beans.Forum;
import com.senla.beans.ForumMessage;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class ForumMessageDAOImpl extends GenericDAOImpl<ForumMessage> implements ForumMessageDAO {
    public ForumMessageDAOImpl() {
        super(ForumMessage.class);
    }

    @Override
    public List<ForumMessage> getForumMessagesByForumId(long id) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ForumMessage> criteriaQuery = criteriaBuilder.createQuery(ForumMessage.class);
        Root<Forum> forumRoot = criteriaQuery.from(Forum.class);
        criteriaQuery.where(criteriaBuilder.equal(forumRoot.get("id"), id));
        Join forumMessageJoin = forumRoot.join("messages", JoinType.INNER);
        criteriaQuery.select(forumMessageJoin);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
