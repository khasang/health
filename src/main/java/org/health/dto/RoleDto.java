package org.health.dto;

import org.health.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleDto {
    private long id;
    private String name;
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoleDto getCloneRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        if (role == null) {
            // TODO не работает, roleDao = null ??????
//            roleDto.getCloneRoleDto(this.roleDao.getEntity(1));
            return roleDto;
        }

        roleDto.id = role.getId();
        roleDto.name = role.getName();
        roleDto.description = role.getDescription();

        return roleDto;
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
