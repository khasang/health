package org.health.dao.impl;

import org.health.dao.ResultExaminationDao;
import org.health.entity.ResultExamination;

public class ResultExaminationDaoImpl extends BasicDaoImpl<ResultExamination> implements ResultExaminationDao {
    public ResultExaminationDaoImpl(Class<ResultExamination> entityClass) {
        super(entityClass);
    }
}
