package org.health.controller;

import org.health.dto.ScheduleDto;
import org.health.entity.Schedule;
import org.health.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    private ScheduleService scheduleService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Schedule addEmployee(@RequestBody Schedule schedule) {
        return scheduleService.addSchedule(schedule);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Schedule updateEmployee(@RequestBody Schedule schedule) {
        return scheduleService.updateSchedule(schedule);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ScheduleDto getEmployeeById(@PathVariable("id") long id) {
        return scheduleService.getSchedule(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<Schedule> getAllEmployees() {
        return scheduleService.getAllSchedules();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Schedule deleteEmployeeById(@PathVariable("id") long id) {
        return scheduleService.deleteSchedule(id);
    }

    @Autowired
    public void setEmployeeService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
}
