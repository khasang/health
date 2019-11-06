package org.health.entity;



import javax.persistence.*;

@Entity(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "role_name", length = 15, nullable = false, unique = true)
    private String name;

    private String description;

    public Role() {
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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj != null && obj.getClass() == this.getClass()) {
            Role role = (Role) obj;
            return getName().equals(role.getName());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "id=" + id + "\n" +
                "name=" + name + "\n" +
                "description=" + description;
    }
}
