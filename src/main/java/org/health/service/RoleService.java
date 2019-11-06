package org.health.service;

import org.health.entity.Role;

import java.util.List;

public interface RoleService {
    /**
     * method required for adding role
     * @param role - role for adding
     * @return created role
     **/
    Role addRole(Role role);

    /**
     * method required for updating role
     * @param role - role for updating
     * @return updated role
     **/
    Role updateRole(Role role);

    /**
     * method required for deletion role by id
     * @param id - id role to delete
     * @return deleted role by id
     **/
    Role deleteRole(long id);

    /**
     * method required for getting role by id
     * @param id - id role to get
     * @return role by id
     **/
    Role getRole(long id);

    /**
     * method required for getting roles
     * @return all roles
     **/
    List<Role> getAllRoles();
}