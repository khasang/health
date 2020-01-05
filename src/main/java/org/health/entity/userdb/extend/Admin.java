package org.health.entity.userdb.extend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.health.entity.userdb.User;

import javax.persistence.*;

@ApiModel(value = "Admin", description = "Class representing the admin.")
@Entity
@Table(name = "admins")
public class Admin {
    @ApiModelProperty(notes = "Unique identifier", required = true, example = "1", position = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private User user;

    @Column(name = "description")
    private String description;

    public Admin() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", user=" + user +
                ", description='" + description + '\'' +
                '}';
    }
}
