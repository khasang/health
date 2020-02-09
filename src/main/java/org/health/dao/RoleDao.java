package org.health.dao;

import org.health.entity.Role;
import org.health.entity.userdb.User;

import java.util.List;

public interface RoleDao extends BasicDao<Role> {
    List<User> getUsersByIdRole(long id);
}
