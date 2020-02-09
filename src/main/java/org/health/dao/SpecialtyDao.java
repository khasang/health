package org.health.dao;

import org.health.entity.Specialty;
import org.health.entity.userdb.extend.Doctor;

import java.util.List;

public interface SpecialtyDao extends BasicDao<Specialty> {
    List<Doctor> getDoctorsByIdSpecialty(long id);

}
