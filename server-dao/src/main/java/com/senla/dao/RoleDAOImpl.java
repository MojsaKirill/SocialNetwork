package com.senla.dao;

import com.senla.api.dao.RoleDAO;
import com.senla.beans.Role;

public class RoleDAOImpl extends GenericDAOImpl<Role> implements RoleDAO {
    public RoleDAOImpl() {
        super(Role.class);
    }
}
