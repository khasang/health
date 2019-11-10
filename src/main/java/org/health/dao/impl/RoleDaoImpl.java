package org.health.dao.impl;

import org.health.entity.Role;
import org.health.dao.RoleDao;

public class RoleDaoImpl extends BasicDaoImpl<Role> implements RoleDao {
    public RoleDaoImpl(Class<Role> entityClass) {
        super(entityClass);
    }
}
