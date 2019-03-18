package com.senla.services;

import com.senla.api.dao.MessageDAO;
import com.senla.api.service.MessageService;
import com.senla.beans.Message;
import com.senla.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDAO messageDAO;

    @Transactional
    @Override
    public void addMessage(long recipientId, String message, User sender) {
        User recipient = new User(recipientId);
        messageDAO.persist(new Message(message, recipient, sender, new Date()));
    }

    @Transactional
    @Override
    public List<Message> getMessagesByUserId(long messageId) {
        return messageDAO.getMessagesByUserId(messageId);
    }

    @Transactional
    @Override
    public void deleteMessage(Message message) {
        messageDAO.delete(message);
    }
}