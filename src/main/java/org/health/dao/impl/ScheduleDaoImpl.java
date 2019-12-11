package org.health.dao.impl;

import org.health.dao.ScheduleDao;
import org.health.entity.Schedule;

public class ScheduleDaoImpl extends BasicDaoImpl<Schedule> implements ScheduleDao {
    public ScheduleDaoImpl(Class<Schedule> entityClass) {
        super(entityClass);
    }
}
