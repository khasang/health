package org.health.entity;

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
public class Inspection {

    /** Field inspection id.*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /** Field date with time.*/
    private LocalDateTime time;

    /** Field room number.*/
    private int room;

    /** Field inspection duration time.*/
    private long duration;

    /** Field price.*/
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
