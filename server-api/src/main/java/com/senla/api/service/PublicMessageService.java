package com.senla.api.service;

import com.senla.beans.PublicMessage;
import com.senla.beans.User;

import java.util.List;

public interface PublicMessageService {
	void addPublicMessage(String message, User user);

	List<PublicMessage> getPublicMessagesByUserId(long userId);

	Boolean deletePublicMessage(long messageId, User user);

    void deletePublicMessage(PublicMessage message);
}
