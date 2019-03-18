package com.senla.services;

import com.senla.api.dao.ForumDAO;
import com.senla.api.service.ForumService;
import com.senla.beans.Forum;
import com.senla.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ForumServiceImpl implements ForumService {
    @Autowired
    private ForumDAO forumDAO;

    @Transactional
    @Override
    public void createForum(Forum forum, User user) {
        forum.setModerator(user);
        forumDAO.persist(forum);
    }

    @Transactional
    @Override
    public Boolean subscribeToForum(long forumId, User user) {
        Forum forum = forumDAO.getById(forumId);
        List<User> subscribers = forum.getSubscribers();
        if (!subscribers.contains(user)) {
            subscribers.add(user);
            forum.setSubscribers(subscribers);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public Boolean unsubscribeToForum(long forumId, User user) {
        Forum forum = forumDAO.getById(forumId);
        return forum.getSubscribers().remove(user);
    }

    @Transactional
    @Override
    public List<User> getForumSubscribers(long forumId) {
        return forumDAO.getForumSubscribersById(forumId);
    }

    @Transactional
    @Override
    public List<Forum> getForumsByUserId(long userId) {
        return forumDAO.getForumsByUserId(userId);
    }

    @Transactional
    @Override
    public Forum getForumById(long forumId) {
        return forumDAO.getById(forumId);
    }

    @Transactional
    @Override
    public Boolean editForum(Forum forum, User user) {
        User moderator = forumDAO.getModeratorByForumId(forum.getId());
        if (moderator.equals(user)) {
            forumDAO.merge(forum);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public Boolean deleteForum(long forumId, User user) {
        Forum forum = forumDAO.getById(forumId);
        if (forum.getModerator().equals(user)) {
            forumDAO.delete(forum);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public List<Forum> findForum(String name) {
        return forumDAO.getForumsByName(name);
    }

    @Transactional
    @Override
    public void deleteForum(Forum forum) {
        forumDAO.delete(forum);
    }
}
