package org.health.service.impl;

import javafx.fxml.LoadException;
import org.health.dao.RoleDao;
import org.health.dto.RoleDto;
import org.health.dto.RoleUserDto;
import org.health.dto.UserDto;
import org.health.entity.Role;
import org.health.entity.userdb.User;
import org.health.service.RoleService;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
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
    public List<RoleDto> getAllRoles() {
        List<Role> allEntities = roleDao.getAllEntities();

        List<RoleDto> roleDtos = new ArrayList<>();
        if (allEntities != null)
            allEntities.forEach(r -> roleDtos.add(new RoleDto(r)));

        roleDtos.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        return roleDtos;
    }

    @Override
    public RoleUserDto getUsersByIdRole(long id) {
        Role role = roleDao.getEntity(id);
        List<User> usersByIdRole = roleDao.getUsersByIdRole(id);

        return new RoleUserDto(role, usersByIdRole);
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
}
