package org.health.dao.impl;

import org.health.dao.AdminDao;
import org.health.dao.UserDao;
import org.health.entity.userdb.User;
import org.health.entity.userdb.extend.Admin;

public class AdminDaoImpl extends BasicDaoImpl<Admin> implements AdminDao {
    public AdminDaoImpl(Class<Admin> entityClass) {
        super(entityClass);
    }
}
