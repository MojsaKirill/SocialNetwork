package com.senla.services;

import com.senla.api.dao.UserDAO;
import com.senla.beans.CustomUser;
import com.senla.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final User user = userDAO.getUserByLogin(s);
        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        return new CustomUser(user);
    }
}
