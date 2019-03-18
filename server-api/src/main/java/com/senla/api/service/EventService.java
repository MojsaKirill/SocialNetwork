package com.senla.api.service;

import com.senla.beans.Event;
import com.senla.beans.User;

import java.util.List;

public interface EventService {
	void createEvent(Event event, User user);

	Boolean subscribeToEvent(long eventId, User user);

	Boolean unsubscribeToEvent(long eventId, User user);

	List<User> getEventSubscribers(long eventId);

	List<Event> getEventsByUserId(long userId);

	Event getEventById(long eventId);

	Boolean editEvent(Event event, User user);

	Boolean deleteEvent(long eventId, User user);

    List<Event> findEvent(String name);

    void deleteEvent(Event event);
}
