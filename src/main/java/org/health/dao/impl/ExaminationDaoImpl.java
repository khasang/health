package org.health.dao.impl;

import org.health.dao.ExaminationDao;
import org.health.entity.Examination;

public class ExaminationDaoImpl extends BasicDaoImpl<Examination> implements ExaminationDao {
    public ExaminationDaoImpl(Class<Examination> entityClass) {
        super(entityClass);
    }
}
