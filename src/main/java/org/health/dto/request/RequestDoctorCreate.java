package org.health.dto.request;

import java.util.List;

public class RequestDoctorCreate {
    private long userId;
    private List<Long> specialtyList;

    public RequestDoctorCreate(long userId, List<Long> specialtyList) {
        this.userId = userId;
        this.specialtyList = specialtyList;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Long> getSpecialtyList() {
        return specialtyList;
    }

    public void setSpecialtyList(List<Long> specialtyList) {
        this.specialtyList = specialtyList;
    }

    @Override
    public String toString() {
        return "RequestDoctorCreate{" +
                "userId=" + userId +
                ", specialtyList=" + specialtyList +
                '}';
    }
}
