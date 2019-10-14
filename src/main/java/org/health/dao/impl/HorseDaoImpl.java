package org.health.dao.impl;

import org.health.dao.HorseDao;
import org.health.entity.Horse;

public class HorseDaoImpl extends BasicDaoImpl<Horse> implements HorseDao {
    public HorseDaoImpl(Class<Horse> entityClass) {
        super(entityClass);
    }
}
