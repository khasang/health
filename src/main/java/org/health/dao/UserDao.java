package org.health.dao;

import org.health.entity.Role;
import org.health.entity.userdb.User;

import java.util.List;

public interface UserDao extends BasicDao<User> {

    List<Role> getRoleSetByUserId(long userId);
}
