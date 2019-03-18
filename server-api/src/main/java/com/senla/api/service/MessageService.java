package com.senla.api.service;

import com.senla.beans.Message;
import com.senla.beans.User;

import java.util.List;

public interface MessageService {
	void addMessage(long recipientId, String message, User sender);

	List<Message> getMessagesByUserId(long messageId);

    void deleteMessage(Message message);
}
