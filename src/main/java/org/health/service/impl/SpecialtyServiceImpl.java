package org.health.service.impl;

import org.health.dao.SpecialtyDao;
import org.health.dto.SpecialtyDoctorDto;
import org.health.dto.SpecialtyDto;
import org.health.entity.Specialty;
import org.health.entity.userdb.extend.Doctor;
import org.health.service.SpecialtyService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

@Service("specialtyService")
public class SpecialtyServiceImpl implements SpecialtyService {
    private SessionFactory sessionFactory;
    private SpecialtyDao specialtyDao;

    @Override
    public Specialty add(Specialty specialty) {
        //TODO не работают транзакции
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Specialty specialtyCheckTitleFree = checkTitleFree(specialty.getTitle());
        Specialty returnSpecialty = specialtyCheckTitleFree != null ? specialtyCheckTitleFree : specialtyDao.addEntity(specialty);
        session.getTransaction().rollback();

        return returnSpecialty;
    }

    @Override
    public Specialty checkTitleFree(String title) {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Specialty> userLoginCriteria = builder.createQuery(Specialty.class);
        Root<Specialty> userLoginRoot = userLoginCriteria.from(Specialty.class);
        userLoginCriteria.select(userLoginRoot);
        userLoginCriteria.where(builder.equal(userLoginRoot.get("title"), title));

        List<Specialty> resultList = sessionFactory.openSession().createQuery(userLoginCriteria).getResultList();
        return resultList.size() == 0
                ? null
                : resultList.get(0);
    }

    @Override
    public Specialty update(Specialty specialty) {
        return specialtyDao.updateEntity(specialty);
    }

    @Override
    public Specialty delete(long id) {
        return specialtyDao.deleteEntity(getById(id));
    }

    @Override
    public Specialty getById(long id) {
        return specialtyDao.getEntity(id);
    }

    @Override
    public List<SpecialtyDto> getAllSpecialtyDto() {
        List<Specialty> allEntities = specialtyDao.getAllEntities();

        List<SpecialtyDto> specialtyDtos = new LinkedList<>();
        if (allEntities != null)
            allEntities.forEach(s -> specialtyDtos.add(new SpecialtyDto(s, specialtyDao.getDoctorsByIdSpecialty(s.getId()).size())));

        specialtyDtos.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        return specialtyDtos;
    }

    @Override
    public SpecialtyDoctorDto getDoctorsByIdSpecialty(long id) {
        Specialty specialty = specialtyDao.getEntity(id);
        List<Doctor> doctorsByIdSpecialty = specialtyDao.getDoctorsByIdSpecialty(id);

        return new SpecialtyDoctorDto(specialty, doctorsByIdSpecialty);
    }

    @Autowired
    public void setSpecialtyDao(SpecialtyDao specialtyDao) {
        this.specialtyDao = specialtyDao;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
