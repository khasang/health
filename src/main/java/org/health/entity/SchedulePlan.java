package org.health.entity;

import javax.persistence.*;


/**
 *  Class for schedule plan
 *
 * @author  ad.art
 */

@Entity
@Table(name = "schedulePlan")
public class SchedulePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "doctorId")
    private long doctorId;

    @Column(name = "scheduleId")
    private long scheduleId;

    @Column(name = "specialityId")
    private long specialityId;

    @Column(name = "roomId")
    private long roomId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    public long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public long getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(long specialityId) {
        this.specialityId = specialityId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }
}
