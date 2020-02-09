package org.health.service;

import org.health.entity.userdb.extend.Doctor;

import java.util.List;

public interface DoctorService {

    Doctor add(Doctor doctor);

    Doctor update(Doctor doctor);

    Doctor delete(long id);

    Doctor getById(long id);

    List<Doctor> getAll();
}
