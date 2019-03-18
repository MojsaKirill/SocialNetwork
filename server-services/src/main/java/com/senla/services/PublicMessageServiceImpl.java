package com.senla.services;

import com.senla.api.dao.PublicMessageDAO;
import com.senla.api.service.PublicMessageService;
import com.senla.beans.PublicMessage;
import com.senla.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PublicMessageServiceImpl implements PublicMessageService {
    @Autowired
    private PublicMessageDAO publicMessageDAO;

    @Transactional
    @Override
    public void addPublicMessage(String message, User user) {
        publicMessageDAO.persist(new PublicMessage(message, user, new Date()));
    }

    @Transactional
    @Override
    public List<PublicMessage> getPublicMessagesByUserId(long userId) {
        return publicMessageDAO.getPublicMessagesByUserId(userId);
    }

    @Transactional
    @Override
    public Boolean deletePublicMessage(long messageId, User user) {
        PublicMessage publicMessage = publicMessageDAO.getById(messageId);
        if (publicMessage.getRecipient().equals(user)) {
            publicMessageDAO.delete(publicMessage);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void deletePublicMessage(PublicMessage publicMessage) {
        publicMessageDAO.delete(publicMessage);
    }
}
