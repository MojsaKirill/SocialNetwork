package com.senla.services;

import com.senla.api.dao.ForumDAO;
import com.senla.api.dao.ForumMessageDAO;
import com.senla.api.service.ForumMessageService;
import com.senla.beans.Forum;
import com.senla.beans.ForumMessage;
import com.senla.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ForumMessageServiceImpl implements ForumMessageService {
    @Autowired
    private ForumMessageDAO forumMessageDAO;
    @Autowired
    private ForumDAO forumDAO;

    @Transactional
    @Override
    public List<ForumMessage> getForumMessages(long forumId) {
        return forumMessageDAO.getForumMessagesByForumId(forumId);
    }

    @Transactional
    @Override
    public void addMessage(String message, User user, long forumId) {
        Forum forum = new Forum();
        forum.setId(forumId);
        ForumMessage forumMessage = new ForumMessage(message, new Date(), user, forum);
        forumMessageDAO.persist(forumMessage);
    }

    @Transactional
    @Override
    public Boolean deleteForumMessage(ForumMessage forumMessage, User user, long forumId) {
        Forum forum = forumDAO.getById(forumId);
        if (forum.getModerator().equals(user)) {
            forumMessageDAO.delete(forumMessage);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void deleteForumMessage(ForumMessage forumMessage) {
        forumMessageDAO.delete(forumMessage);
    }
}
