package com.senla.api.service;

import com.senla.beans.ForumMessage;
import com.senla.beans.User;

import java.util.List;

public interface ForumMessageService {
    List<ForumMessage> getForumMessages(long forumId);

    void addMessage(String message, User user, long forumId);

    Boolean deleteForumMessage(ForumMessage forumMessage, User user, long forumId);

    void deleteForumMessage(ForumMessage forumMessage);
}
