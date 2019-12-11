package org.health.db;

import org.health.entity.Schedule;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ScheduleRepository extends PagingAndSortingRepository<Schedule, Long> {
}
