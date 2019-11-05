package org.health.service.impl;

import org.health.dao.ResultExaminationDao;
import org.health.entity.ResultExamination;
import org.health.service.ResultExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("resultExaminationService")
public class ResultExaminationServiceImpl implements ResultExaminationService {
    private ResultExaminationDao resultExaminationDao;

    @Override
    public ResultExamination addResultExamination(ResultExamination resultExamination) {
        return resultExaminationDao.addEntity(resultExamination);
    }

    @Override
    public ResultExamination updateResultExamination(ResultExamination resultExamination) {
        return resultExaminationDao.updateEntity(resultExamination);
    }

    @Override
    public ResultExamination getResultExamination(long id) {
        return resultExaminationDao.getEntity(id);
    }

    @Override
    public List<ResultExamination> getAllResultExaminations() {
        return resultExaminationDao.getAllEntities();
    }

    @Override
    public ResultExamination deleteResultExamination(long id) {
        return resultExaminationDao.deleteEntity(this.getResultExamination(id));
    }

    @Autowired
    public void setResultExaminationDao(ResultExaminationDao resultExaminationDao) {
        this.resultExaminationDao = resultExaminationDao;
    }
}
