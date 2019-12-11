package org.health.service.impl;

import org.health.dao.ScheduleDao;
import org.health.dto.ScheduleDto;
import org.health.entity.Schedule;
import org.health.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {
    private ScheduleDao scheduleDao;
    private ScheduleDto scheduleDto;

    @Override
    public Schedule addSchedule(Schedule schedule) {
        return scheduleDao.addEntity(schedule);
    }

    @Override
    public Schedule updateSchedule(Schedule schedule) {
        return scheduleDao.updateEntity(schedule);
    }

    @Override
    public ScheduleDto getSchedule(long id) {
        return scheduleDto.getScheduleDto(scheduleDao.getEntity(id));
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleDao.getAllEntities();
    }

    @Override
    public Schedule deleteSchedule(long id) {
        return scheduleDao.deleteEntity(scheduleDao.getEntity(id));
    }

    @Autowired
    public void setScheduleDao(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    @Autowired
    public void setScheduleDto(ScheduleDto scheduleDto) {
        this.scheduleDto = scheduleDto;
    }
}
