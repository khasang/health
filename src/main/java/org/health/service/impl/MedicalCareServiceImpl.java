package org.health.service.impl;

import java.util.*;

import org.health.dao.*;
import org.health.entity.*;
import org.health.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service("medicalCareService")
public class MedicalCareServiceImpl implements MedicalCareService {
    private MedicalCareDao medicalCareDao;

    @Override
    public MedicalCare addMedicalCare(MedicalCare medicalCare) {
        return medicalCareDao.addEntity(medicalCare);
    }

    @Override
    public MedicalCare updateMedicalCare(MedicalCare medicalCare) {
        return medicalCareDao.updateEntity(medicalCare);
    }

    @Override
    public MedicalCare getMedicalCare(long id) {
        return medicalCareDao.getEntity(id);
    }

    @Override
    public List<MedicalCare> getAllMedicalCares() {
        return medicalCareDao.getAllEntities();
    }

    @Override
    public MedicalCare deleteMedicalCare(long id) {
        return medicalCareDao.deleteEntity(medicalCareDao.getEntity(id));
    }

    @Autowired
    public void setMedicalCareDao(MedicalCareDao medicalCareDao) {
        this.medicalCareDao = medicalCareDao;
    }
}
