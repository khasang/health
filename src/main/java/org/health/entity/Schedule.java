package org.health.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

/**
 *  Class for working schedule
 *
 * @author  ad.art
 */

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")  // 0 - week, 1 - odd, 2 - even, 3 - custom
    private Integer type;


    @Temporal(TemporalType.TIME)
    private Date day1Begin;
    @Temporal(TemporalType.TIME)
    private Date day1End;

    @Temporal(TemporalType.TIME)
    private Date day2Begin;
    @Temporal(TemporalType.TIME)
    private Date day2End;

    @Temporal(TemporalType.TIME)
    private Date day3Begin;
    @Temporal(TemporalType.TIME)
    private Date day3End;

    @Temporal(TemporalType.TIME)
    private Date day4Begin;

    @DateTimeFormat
    @Temporal(TemporalType.TIME)
    private Date day4End;

    @Temporal(TemporalType.TIME)
    private Date day5Begin;
    @Temporal(TemporalType.TIME)
    private Date day5End;

    @Temporal(TemporalType.TIME)
    private Date day6Begin;
    @Temporal(TemporalType.TIME)
    private Date day6End;

    @Temporal(TemporalType.TIME)
    private Date day7Begin;
    @Temporal(TemporalType.TIME)
    private Date day7End;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getDay1Begin() {
        return day1Begin;
    }

    public void setDay1Begin(Date day1Begin) {
        this.day1Begin = day1Begin;
    }

    public Date getDay1End() {
        return day1End;
    }

    public void setDay1End(Date day1End) {
        this.day1End = day1End;
    }

    public Date getDay2Begin() {
        return day2Begin;
    }

    public void setDay2Begin(Date day2Begin) {
        this.day2Begin = day2Begin;
    }

    public Date getDay2End() {
        return day2End;
    }

    public void setDay2End(Date day2End) {
        this.day2End = day2End;
    }

    public Date getDay3Begin() {
        return day3Begin;
    }

    public void setDay3Begin(Date day3Begin) {
        this.day3Begin = day3Begin;
    }

    public Date getDay3End() {
        return day3End;
    }

    public void setDay3End(Date day3End) {
        this.day3End = day3End;
    }

    public Date getDay4Begin() {
        return day4Begin;
    }

    public void setDay4Begin(Date day4Begin) {
        this.day4Begin = day4Begin;
    }

    public Date getDay4End() {
        return day4End;
    }

    public void setDay4End(Date day4End) {
        this.day4End = day4End;
    }

    public Date getDay5Begin() {
        return day5Begin;
    }

    public void setDay5Begin(Date day5Begin) {
        this.day5Begin = day5Begin;
    }

    public Date getDay5End() {
        return day5End;
    }

    public void setDay5End(Date day5End) {
        this.day5End = day5End;
    }

    public Date getDay6Begin() {
        return day6Begin;
    }

    public void setDay6Begin(Date day6Begin) {
        this.day6Begin = day6Begin;
    }

    public Date getDay6End() {
        return day6End;
    }

    public void setDay6End(Date day6End) {
        this.day6End = day6End;
    }

    public Date getDay7Begin() {
        return day7Begin;
    }

    public void setDay7Begin(Date day7Begin) {
        this.day7Begin = day7Begin;
    }

    public Date getDay7End() {
        return day7End;
    }

    public void setDay7End(Date day7End) {
        this.day7End = day7End;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj != null && obj.getClass() == this.getClass()) {
            Schedule schedule = (Schedule) obj;
            return getId() == schedule.getId();
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.name + " " +  getType().toString();
    }
}
