package com.senla.web;

import com.senla.api.service.*;
import com.senla.beans.*;
import com.senla.data.WebConstants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin", produces = WebConstants.APPLICATION_JSON)
public class AdminController {
    @Autowired
    private EventService eventService;
    @Autowired
    private ForumMessageService forumMessageService;
    @Autowired
    private ForumService forumService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private PublicMessageService publicMessageService;
    @Autowired
    private UserService userService;

    private static final Logger LOGGER = Logger.getLogger(AdminController.class);

    @RequestMapping(value = "/deleteEvent", method = RequestMethod.DELETE)
    public Boolean deleteEvent(@RequestBody Event event) {
        try {
            eventService.deleteEvent(event);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    @RequestMapping(value = "/deleteForumMessage", method = RequestMethod.DELETE)
    public Boolean deleteForumMessage(@RequestBody ForumMessage forumMessage) {
        try {
            forumMessageService.deleteForumMessage(forumMessage);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    @RequestMapping(value = "/deleteForum", method = RequestMethod.DELETE)
    public Boolean deleteForum(@RequestBody Forum forum) {
        try {
            forumService.deleteForum(forum);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    @RequestMapping(value = "/deleteMessage", method = RequestMethod.DELETE)
    public Boolean deleteMessage(@RequestBody Message message) {
        try {
            messageService.deleteMessage(message);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    @RequestMapping(value = "/deletePublicMessage", method = RequestMethod.DELETE)
    public Boolean deletePublicMessage(@RequestBody PublicMessage publicMessage) {
        try {
            publicMessageService.deletePublicMessage(publicMessage);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public Boolean deleteUser(@RequestBody User user) {
        try {
            userService.deleteUser(user);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }
}
