package com.senla.web;

import com.senla.web.annotations.CurrentUser;
import com.senla.api.service.MessageService;
import com.senla.beans.CustomUser;
import com.senla.beans.Message;
import com.senla.data.WebConstants;
import com.senla.web.dto.MessageDto;
import com.senla.web.dto.SimpleDto;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/messages", produces = WebConstants.APPLICATION_JSON)
public class MessageController {
	@Autowired
	private MessageService messageService;
	@Autowired
	private ModelMapper modelMapper;

	private static final Logger LOGGER = Logger.getLogger(MessageController.class);

	@RequestMapping(method = RequestMethod.GET)
	public List<MessageDto> getMessages(@CurrentUser CustomUser customUser) {
		try {
			List<Message> messages = messageService.getMessagesByUserId(customUser.getId());
			List<MessageDto> messagesDto = new ArrayList<>();
			for (Message message : messages) {
				MessageDto messageDto = modelMapper.map(message, MessageDto.class);
				messagesDto.add(messageDto);
			}
			return messagesDto;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public Boolean sendMessage(@PathVariable(value = "id") long id, @RequestBody SimpleDto<String> message,
							   @CurrentUser CustomUser customUser) {
		try {
			messageService.addMessage(id, message.getData(), customUser.getCurrentUser());
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return false;
		}
	}

}
