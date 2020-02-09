package org.health.dao.impl;

import org.health.dao.RoleDao;
import org.health.entity.Role;
import org.health.entity.userdb.User;

import java.util.List;

public class RoleDaoImpl extends BasicDaoImpl<Role> implements RoleDao {
    public RoleDaoImpl(Class<Role> entityClass) {
        super(entityClass);
    }

    @Override
    public List<User> getUsersByIdRole(long id) {
        List<User> users = getSession().load(Role.class, id).getUsers();
        return users.size() > 0
                ? users
                : null;
    }
}
