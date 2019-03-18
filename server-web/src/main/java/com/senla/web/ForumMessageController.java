package com.senla.web;

import com.senla.web.annotations.CurrentUser;
import com.senla.api.service.ForumMessageService;
import com.senla.beans.CustomUser;
import com.senla.beans.ForumMessage;
import com.senla.data.WebConstants;
import com.senla.web.dto.ForumMessageDto;
import com.senla.web.dto.SimpleDto;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/forumMessages", produces = WebConstants.APPLICATION_JSON)
public class ForumMessageController {
    @Autowired
    private ForumMessageService forumMessageService;
    @Autowired
    private ModelMapper modelMapper;

    private static final Logger LOGGER = Logger.getLogger(ForumMessageController.class);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<ForumMessageDto> getForumMessage(@PathVariable(value = "id") long id) {
        try {
            List<ForumMessage> forumMessages = forumMessageService.getForumMessages(id);
            List<ForumMessageDto> forumMessagesDto = new ArrayList<>();
            for (ForumMessage forumMessage : forumMessages) {
                ForumMessageDto forumMessageDto = modelMapper.map(forumMessage, ForumMessageDto.class);
                forumMessagesDto.add(forumMessageDto);
            }
            return forumMessagesDto;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Boolean sendForumMessage(@PathVariable(value = "id") long id, @RequestBody SimpleDto<String> message,
                                    @CurrentUser CustomUser customUser) {
        try {
            forumMessageService.addMessage(message.getData(), customUser.getCurrentUser(), id);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean deleteMessage(@PathVariable(value = "id") long id, @CurrentUser CustomUser customUser,
                                 @RequestBody ForumMessage forumMessage) {
        try {
            return forumMessageService.deleteForumMessage(forumMessage, customUser.getCurrentUser(), id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }
}
