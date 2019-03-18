package com.senla.web;

import com.senla.web.annotations.CurrentUser;
import com.senla.api.service.ForumService;
import com.senla.beans.CustomUser;
import com.senla.beans.Forum;
import com.senla.beans.User;
import com.senla.data.WebConstants;
import com.senla.web.dto.ForumDto;
import com.senla.web.dto.SimpleDto;
import com.senla.web.dto.UserDto;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/forums", produces = WebConstants.APPLICATION_JSON)
public class ForumController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ForumService forumService;

    private static final Logger LOGGER = Logger.getLogger(ForumController.class);

    @RequestMapping(method = RequestMethod.GET)
    public List<ForumDto> getForums(@CurrentUser CustomUser customUser) {
        try {
            List<Forum> forums = forumService.getForumsByUserId(customUser.getId());
            List<ForumDto> forumsDTO = new ArrayList<>();
            for (Forum forum : forums) {
                ForumDto forumDto;
                forumDto = modelMapper.map(forum, ForumDto.class);
                forumsDTO.add(forumDto);
            }
            return forumsDTO;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Boolean addForum(@RequestBody Forum forum, @CurrentUser CustomUser customUser) {
        try {
            forumService.createForum(forum, customUser.getCurrentUser());
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/subscribe/{id}", method = RequestMethod.POST)
    public Boolean subscribeToForum(@PathVariable(value = "id") long id, @CurrentUser CustomUser customUser) {
        try {
            return forumService.subscribeToForum(id, customUser.getCurrentUser());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    @RequestMapping(value = "/unsubscribe/{id}", method = RequestMethod.POST)
    public Boolean unsubscribeToForum(@PathVariable(value = "id") long id, @CurrentUser CustomUser customUser) {
        try {
            return forumService.unsubscribeToForum(id, customUser.getCurrentUser());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    @RequestMapping(value = "/subscribers/{id}", method = RequestMethod.GET)
    public List<UserDto> getSubscribers(@PathVariable(value = "id") long id) {
        try {
            List<User> subscribers = forumService.getForumSubscribers(id);
            List<UserDto> usersDto = new ArrayList<>();
            for (User user : subscribers) {
                UserDto userDto = modelMapper.map(user, UserDto.class);
                usersDto.add(userDto);
            }
            return usersDto;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ForumDto getForum(@PathVariable(value = "id") long id) {
        try {
            ForumDto forumDto = modelMapper.map(forumService.getForumById(id), ForumDto.class);
            return forumDto;
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Boolean editForum(@RequestBody Forum forum, @CurrentUser CustomUser customUser) {
        try {
            return forumService.editForum(forum, customUser.getCurrentUser());
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean deleteForum(@PathVariable(value = "id") long id, @CurrentUser CustomUser customUser) {
        try {
            return forumService.deleteForum(id, customUser.getCurrentUser());
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public List<ForumDto> findForum(@RequestBody SimpleDto<String> name) {
        try {
            List<Forum> forums = forumService.findForum(name.getData());
            List<ForumDto> forumsDto = new ArrayList<>();
            for (Forum forum : forums) {
                forumsDto.add(modelMapper.map(forum, ForumDto.class));
            }
            return forumsDto;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}
