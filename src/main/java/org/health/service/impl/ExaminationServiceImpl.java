package org.health.service.impl;

import org.health.dao.ExaminationDao;
import org.health.entity.Examination;
import org.health.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("examinationService")
public class ExaminationServiceImpl implements ExaminationService {
    private ExaminationDao examinationDao;

    @Override
    public Examination addExamination(Examination examination) {
        return this.examinationDao.addEntity(examination);
    }

    @Override
    public Examination updateExamination(Examination examination) {
        return examinationDao.updateEntity(examination);
    }

    @Override
    public Examination getExaminationById(long id) {
        return this.examinationDao.getEntity(id);
    }

    @Override
    public List<Examination> getAllExaminations() {
        return this.examinationDao.getAllEntities();
    }

    @Override
    public Examination deleteExaminationById(long id) {
        return this.examinationDao.deleteEntity(this.examinationDao.getEntity(id));
    }

    @Autowired
    public void setExaminationDao(ExaminationDao examinationDao) {
        this.examinationDao = examinationDao;
    }
}
