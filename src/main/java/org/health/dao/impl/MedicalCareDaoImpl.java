package org.health.dao.impl;

import org.health.dao.*;
import org.health.entity.*;

public class MedicalCareDaoImpl extends BasicDaoImpl<MedicalCare> implements MedicalCareDao {
    public MedicalCareDaoImpl(Class<MedicalCare> entityClass) {
        super(entityClass);
    }
}
