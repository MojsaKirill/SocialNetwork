package com.senla.api.service;

import com.senla.beans.Forum;
import com.senla.beans.User;

import java.util.List;

public interface ForumService {
    void createForum(Forum forum, User user);

    Boolean subscribeToForum(long eventId, User user);

    Boolean unsubscribeToForum(long eventId, User user);

    List<User> getForumSubscribers(long forumId);

    List<Forum> getForumsByUserId(long userId);

    Forum getForumById(long forumId);

    Boolean editForum(Forum forum, User user);

    Boolean deleteForum(long forumId, User user);

    List<Forum> findForum(String name);

    void deleteForum(Forum forum);
}
