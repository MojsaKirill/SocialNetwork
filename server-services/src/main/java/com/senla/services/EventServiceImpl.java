package com.senla.services;

import com.senla.api.dao.EventDAO;
import com.senla.api.service.EventService;
import com.senla.beans.Event;
import com.senla.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventDAO eventDAO;

    @Transactional
    @Override
    public void createEvent(Event event, User user) {
        event.setModerator(user);
        eventDAO.persist(event);
    }

    @Transactional
    @Override
    public Boolean subscribeToEvent(long eventId, User user) {
        Event event = eventDAO.getById(eventId);
        List<User> subscribers = event.getSubscribers();
        if (!subscribers.contains(user)) {
            subscribers.add(user);
            event.setSubscribers(subscribers);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public Boolean unsubscribeToEvent(long eventId, User user) {
        Event event = eventDAO.getById(eventId);
        return event.getSubscribers().remove(user);
    }

    @Transactional
    @Override
    public List<User> getEventSubscribers(long eventId) {
        return eventDAO.getEventSubscribersById(eventId);
    }

    @Transactional
    @Override
    public List<Event> getEventsByUserId(long userId) {
        return eventDAO.getEventsByUserId(userId);
    }

    @Transactional
    @Override
    public Event getEventById(long eventId) {
        return eventDAO.getById(eventId);
    }

    @Transactional
    @Override
    public Boolean editEvent(Event event, User user) {
        User moderator = eventDAO.getModeratorByEventId(event.getId());
        if (moderator.equals(user)) {
            eventDAO.merge(event);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public Boolean deleteEvent(long eventId, User user) {
        Event event = eventDAO.getById(eventId);
        if (event.getModerator().equals(user)) {
            eventDAO.delete(event);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public List<Event> findEvent(String name) {
        return eventDAO.getEventsByName(name);
    }

    @Transactional
    @Override
    public void deleteEvent(Event event) {
        eventDAO.delete(event);
    }
}
