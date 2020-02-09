package org.health.dto;

import org.health.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleDto {
    private long id;
    private String name;
    private String description;

    public RoleDto() {
    }

    public RoleDto(Role role) {
        id = role.getId();
        name = role.getName();
        description = role.getDescription();
    }

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

    @Override
    public String toString() {
        return "RoleDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
