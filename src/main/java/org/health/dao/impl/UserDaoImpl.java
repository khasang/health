package org.health.dao.impl;

import org.health.dao.*;
import org.health.entity.userdb.User;

public class UserDaoImpl extends BasicDaoImpl<User> implements UserDao {
    public UserDaoImpl(Class<User> entityClass) {
        super(entityClass);
    }
}
