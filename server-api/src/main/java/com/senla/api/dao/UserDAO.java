package com.senla.api.dao;

import com.senla.beans.User;

import java.util.List;

public interface UserDAO extends GenericDao<User> {
    User getUserByLogin(String login);

    List<User> getUsersByFullName(String lastName, String firstName);

    List<User> getFriendsByUserId(long id);
}
