package org.health.dao.impl;

import org.health.dao.SpecialtyDao;
import org.health.entity.Specialty;
import org.health.entity.userdb.extend.Doctor;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class SpecialtyDaoImpl extends BasicDaoImpl<Specialty> implements SpecialtyDao {
    public SpecialtyDaoImpl(Class<Specialty> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Doctor> getDoctorsByIdSpecialty(long id) {
        List<Doctor> doctors = getSession().load(Specialty.class, id).getDoctors();
        return doctors.size() > 0
                ? doctors
                : null;
    }
}
