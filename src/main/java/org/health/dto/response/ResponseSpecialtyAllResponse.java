package org.health.dto.response;

import org.health.entity.Specialty;

public class ResponseSpecialtyAllResponse {
    private long id;
    private String title;

    public ResponseSpecialtyAllResponse(Specialty specialty) {
        this.id = specialty.getId();
        this.title = specialty.getTitle();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ResponseSpecialtyAllResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
