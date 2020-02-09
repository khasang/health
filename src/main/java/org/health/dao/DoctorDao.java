package org.health.dao;

import org.health.entity.Role;
import org.health.entity.userdb.extend.Doctor;

public interface DoctorDao extends BasicDao<Doctor> {
    Doctor getUserByIdDoctor(long userId);

    Role getMyRole();
}
