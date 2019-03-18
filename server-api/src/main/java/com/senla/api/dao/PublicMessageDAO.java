package com.senla.api.dao;

import com.senla.beans.PublicMessage;

import java.util.List;

public interface PublicMessageDAO extends GenericDao<PublicMessage> {
	List<PublicMessage> getPublicMessagesByUserId(long id);
}
