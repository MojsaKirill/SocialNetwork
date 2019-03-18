package com.senla.api.dao;

import com.senla.beans.Event;
import com.senla.beans.User;

import java.util.List;

public interface EventDAO extends GenericDao<Event>{
	List<Event> getEventsByUserId(long id);

	List<User> getEventSubscribersById(long id);

	User getModeratorByEventId(long id);

    List<Event> getEventsByName(String name);
}
