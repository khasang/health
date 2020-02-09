package org.health.dto.response;

import org.health.entity.Specialty;
import org.health.entity.userdb.extend.Doctor;

import java.util.ArrayList;

public class ResponseSpecialty {
    private long id;
    private String title;
    private ArrayList<ResponseDoctor> doctors;

    public ResponseSpecialty(Specialty specialty) {
        this.id = specialty.getId();
        this.title = specialty.getTitle();

        this.doctors = new ArrayList<>();
        for (Doctor doctor : specialty.getDoctors()) {
            this.doctors.add(new ResponseDoctor(doctor));
        }
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

    public ArrayList<ResponseDoctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(ArrayList<ResponseDoctor> doctors) {
        this.doctors = doctors;
    }

    @Override
    public String toString() {
        return "ResponseSpecialty{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", doctors=" + doctors +
                '}';
    }
}
