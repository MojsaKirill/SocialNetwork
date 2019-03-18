package com.senla.web;

import com.senla.api.service.UserService;
import com.senla.beans.User;
import com.senla.data.WebConstants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(produces = WebConstants.APPLICATION_JSON)
public class AuthController {
    @Autowired
    private UserService userService;

    private static final Logger LOGGER = Logger.getLogger(AuthController.class);

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Boolean register(@RequestBody User newUser) {
        try {
            userService.createUser(newUser);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Boolean logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                new SecurityContextLogoutHandler().logout(request, response, authentication);
            }
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }
}
