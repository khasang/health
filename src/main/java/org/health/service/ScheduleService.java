package org.health.service;

import org.health.dto.ScheduleDto;
import org.health.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    /**
     * method required for adding Schedule
     *
     * @param schedule - schedule for adding
     * @return created schedule
     */
    Schedule addSchedule(Schedule schedule);

    /**
     * method required for updating schedule
     *
     * @parm schedule - Schedule for update
     * @return updated Schedule
     */
    Schedule updateSchedule(Schedule schedule);

    /**
     * method required for getting schedule by id
     *
     * @param id - id schedule got getting
     * @return schedule by id
     */
    ScheduleDto getSchedule(long id);

    /**
     * method required for getting all schedules
     *
     * @return all schedules
     */
    List<Schedule> getAllSchedules();

    /**
     * method required for deletion schedule by id
     *
     * @param id - id schedule got delete
     * @return deleted schedule by id
     */
    Schedule deleteSchedule (long id);
}
