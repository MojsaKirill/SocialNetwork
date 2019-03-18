package com.senla.api.dao;

import com.senla.beans.Forum;
import com.senla.beans.User;

import java.util.List;

public interface ForumDAO extends GenericDao<Forum>{
	List<Forum> getForumsByUserId(long id);

	List<User> getForumSubscribersById(long id);

	User getModeratorByForumId(long id);

    List<Forum> getForumsByName(String name);
}
