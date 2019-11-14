package org.health.entity;

import javax.persistence.*;

import io.swagger.annotations.*;

@ApiModel(value = "Medical care", description = "Class representing the medical care.")
@Entity
@Table(name = "medical_cares")
public class MedicalCare {
    @ApiModelProperty(notes = "Unique identifier", required = true, example = "1", position = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ApiModelProperty(notes = "Code ", required = true, example = "1001", position = 2)
    private long cod;

    @ApiModelProperty(notes = "Name", required = true, example = "consultation", position = 3)
    private String name;

    @ApiModelProperty(notes = "Description", required = true, example = "general practitioner consultation", position = 4)
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCod() {
        return cod;
    }

    public void setCod(long cod) {
        this.cod = cod;
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
}
