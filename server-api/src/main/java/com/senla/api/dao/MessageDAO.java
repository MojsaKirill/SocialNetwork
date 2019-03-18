package com.senla.api.dao;

import com.senla.beans.Message;

import java.util.List;

public interface MessageDAO extends GenericDao<Message>{
	List<Message> getMessagesByUserId(long id);
}
