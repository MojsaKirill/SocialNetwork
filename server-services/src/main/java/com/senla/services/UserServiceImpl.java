package com.senla.services;

import com.senla.api.dao.RoleDAO;
import com.senla.api.dao.UserDAO;
import com.senla.api.service.UserService;
import com.senla.beans.Role;
import com.senla.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleDAO roleDAO;

    @Transactional
    @Override
    public User getUserById(long id) {
        return userDAO.getById(id);
    }

    @Transactional
    @Override
    public void createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role role = roleDAO.getById(1);
        roles.add(role);
        user.setRoles(roles);
        userDAO.persist(user);
    }

    @Transactional
    @Override
    public Boolean updateUser(User user, User currentUser) {
        user.setLogin(currentUser.getLogin());
        user.setPassword(currentUser.getPassword());
        if (currentUser.equals(user) && user.getUserInfo().getId() == currentUser.getUserInfo().getId()
                && user.getUserAddress().getId() == currentUser.getUserAddress().getId()) {
            currentUser.setUserAddress(user.getUserAddress());
            currentUser.setUserInfo(user.getUserInfo());
            userDAO.merge(currentUser);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public List<User> getFriendsByUserId(long userId) {
        return userDAO.getFriendsByUserId(userId);
    }

    @Transactional
    @Override
    public User getUserByLogin(String login) {
        return userDAO.getUserByLogin(login);
    }

    @Transactional
    @Override
    public Boolean addFriend(long userId, long friendId) {
        User user = userDAO.getById(userId);
        User friend = userDAO.getById(friendId);
        List<User> friends = user.getFriends();
        if (!friends.contains(friend)) {
            friends.add(friend);
            user.setFriends(friends);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public Boolean deleteFriend(long userId, long friendId) {
        User user = userDAO.getById(userId);
        User friend = getUserById(friendId);
        return user.getFriends().remove(friend);
    }

    @Transactional
    @Override
    public List<User> getUsersByFullName(String lastName, String firstName) {
        return userDAO.getUsersByFullName(lastName, firstName);
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
        userDAO.delete(user);
    }
}
