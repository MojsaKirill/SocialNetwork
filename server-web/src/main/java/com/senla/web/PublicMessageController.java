package com.senla.web;

import com.senla.web.annotations.CurrentUser;
import com.senla.api.service.PublicMessageService;
import com.senla.beans.CustomUser;
import com.senla.beans.PublicMessage;
import com.senla.data.WebConstants;
import com.senla.web.dto.PublicMessageDto;
import com.senla.web.dto.SimpleDto;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/publicMessages", produces = WebConstants.APPLICATION_JSON)
public class PublicMessageController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PublicMessageService publicMessageService;

    private static final Logger LOGGER = Logger.getLogger(PublicMessageController.class);

    @RequestMapping(method = RequestMethod.GET)
    public List<PublicMessageDto> getPublicMessages(@CurrentUser CustomUser customUser) {
        try {
            List<PublicMessage> publicMessages = publicMessageService.getPublicMessagesByUserId(customUser.getId());
            List<PublicMessageDto> publicMessagesDto = new ArrayList<>();
            for (PublicMessage publicMessage : publicMessages) {
                PublicMessageDto publicMessageDto = modelMapper.map(publicMessage, PublicMessageDto.class);
                publicMessagesDto.add(publicMessageDto);
            }
            return publicMessagesDto;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Boolean sendPublicMessages(@RequestBody SimpleDto<String> message, @CurrentUser CustomUser customUser) {
        try {
            publicMessageService.addPublicMessage(message.getData(), customUser.getCurrentUser());
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<PublicMessageDto> getUserPublicMessages(@PathVariable(value = "id") long id) {
        try {
            List<PublicMessage> publicMessages = publicMessageService.getPublicMessagesByUserId(id);
            List<PublicMessageDto> publicMessagesDto = new ArrayList<>();
            for (PublicMessage publicMessage : publicMessages) {
                PublicMessageDto publicMessageDto = modelMapper.map(publicMessage, PublicMessageDto.class);
                publicMessagesDto.add(publicMessageDto);
            }
            return publicMessagesDto;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean deletePublicMessage(@PathVariable(value = "id") long id, @CurrentUser CustomUser customUser) {
        try {
            return publicMessageService.deletePublicMessage(id, customUser.getCurrentUser());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }
}
