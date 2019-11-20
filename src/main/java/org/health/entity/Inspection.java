package org.health.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Class Inspection.
 *
 * @author v.kupriyanov
 * @version 1
 * @since 05.11.2019
 */
@Entity
@Table(name = "inspections")
@ApiModel(value = "Inspection", description = "Class representing an inspection of patient.")
public class Inspection {

    /** Field inspection id.*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "Unique identifier of inspection", required = true, example = "1", position = 1)
    private long id;

    /** Field date with time.*/
    @ApiModelProperty(notes = "Inspection starting time", example = "2019-11-10T00:06:05.762Z", required = true, position = 2)
    private LocalDateTime time;

    /** Field inspection duration time.*/
    @ApiModelProperty(notes = "Inspection duration time", required = true, position = 3)
    private long duration;

    /** Field room number.*/
    @ApiModelProperty(notes = "Inspection room", required = true, example = "114", position = 4)
    private int room;

    /** Field price.*/
    @ApiModelProperty(notes = "Price for inspection", required = true, example = "1500", position = 5)
    private int price;

    /** Field id getter.*/
    public long getId() {
        return id;
    }

    /** Field id setter.*/
    public void setId(long id) {
        this.id = id;
    }

    /** Field time getter.*/
    public LocalDateTime getTime() {
        return time;
    }

    /** Field time setter.*/
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /** Field root getter.*/
    public int getRoom() {
        return room;
    }

    /** Field root setter.*/
    public void setRoom(int room) {
        this.room = room;
    }

    /** Field duration getter.*/
    public long getDuration() {
        return duration;
    }

    /** Field duration setter.*/
    public void setDuration(long duration) {
        this.duration = duration;
    }

    /** Field price getter.*/
    public int getPrice() {
        return price;
    }

    /** Field price setter.*/
    public void setPrice(int price) {
        this.price = price;
    }
}
