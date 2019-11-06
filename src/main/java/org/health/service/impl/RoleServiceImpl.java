package org.health.service.impl;

import org.health.dao.RoleDao;
import org.health.entity.Role;
import org.health.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    @Override
    public Role addRole(Role role) {
        return roleDao.addEntity(role);
    }

    @Override
    public Role updateRole(Role role) {
        return roleDao.updateEntity(role);
    }

    @Override
    public Role deleteRole(long id) {
        return roleDao.deleteEntity(getRole(id));
    }

    @Override
    public Role getRole(long id) {
        return roleDao.getEntity(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllEntities();
    }

    @Autowired
    public void setRoleDao(RoleDao horseDao) {
        this.roleDao = horseDao;
    }
}
