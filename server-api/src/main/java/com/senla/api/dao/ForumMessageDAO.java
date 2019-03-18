package com.senla.api.dao;

import com.senla.beans.ForumMessage;

import java.util.List;

public interface ForumMessageDAO extends GenericDao<ForumMessage> {
    List<ForumMessage> getForumMessagesByForumId(long id);
}
