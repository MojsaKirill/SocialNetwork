package com.senla.api.service;

import com.senla.beans.User;

import java.util.List;

public interface UserService {
	List<User> getFriendsByUserId(long userId);

	User getUserByLogin(String login);

	User getUserById(long id);

	void createUser(User user);

	Boolean updateUser(User user, User currentUser);

	Boolean addFriend(long userId, long friendId);

	Boolean deleteFriend(long userId, long friendId);

	List<User> getUsersByFullName(String lastName, String firstName);

	void deleteUser(User user);
}
