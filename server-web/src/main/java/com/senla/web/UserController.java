package com.senla.web;

import com.senla.web.annotations.CurrentUser;
import com.senla.api.service.UserService;
import com.senla.beans.CustomUser;
import com.senla.beans.User;
import com.senla.data.WebConstants;
import com.senla.web.dto.SearchDto;
import com.senla.web.dto.UserDto;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = WebConstants.APPLICATION_JSON)
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @RequestMapping(method = RequestMethod.GET)
    public UserDto getProfile(@CurrentUser CustomUser customUser) {
        try {
            UserDto userDto = modelMapper.map(customUser.getCurrentUser(), UserDto.class);
            return userDto;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDto getUser(@PathVariable(value = "id") long id) {
        try {
            UserDto userDto = modelMapper.map(userService.getUserById(id), UserDto.class);
            return userDto;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public List<UserDto> getFriends(@CurrentUser CustomUser customUser) {
        try {
            List<UserDto> usersDto = new ArrayList<>();
            List<User> friends = userService.getFriendsByUserId(customUser.getId());
            for (User user : friends) {
                UserDto userDto = modelMapper.map(user, UserDto.class);
                usersDto.add(userDto);
            }
            return usersDto;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Boolean editUser(@RequestBody User user, @CurrentUser CustomUser customUser) {
        try {
            return userService.updateUser(user, customUser.getCurrentUser());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }


    @RequestMapping(value = "/addFriend/{id}", method = RequestMethod.POST)
    public Boolean addFriend(@PathVariable(value = "id") long id, @CurrentUser CustomUser customUser) {
        try {
            return userService.addFriend(customUser.getId(), id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    @RequestMapping(value = "/delFriend/{id}", method = RequestMethod.POST)
    public Boolean delFriend(@PathVariable(value = "id") long id, @CurrentUser CustomUser customUser) {
        try {
            return userService.deleteFriend(customUser.getId(), id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public List<UserDto> findUser(@RequestBody SearchDto searchDto) {
        try {
            List<UserDto> usersDto = new ArrayList<>();
            List<User> users = userService.getUsersByFullName(searchDto.getLastName(), searchDto.getFirstName());
            for (User user : users) {
                UserDto userDto = modelMapper.map(user, UserDto.class);
                usersDto.add(userDto);
            }
            return usersDto;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}
