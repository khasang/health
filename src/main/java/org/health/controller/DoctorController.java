package org.health.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.health.dto.response.ResponseDoctor;
import org.health.entity.userdb.extend.Admin;
import org.health.entity.userdb.extend.Doctor;
import org.health.service.AdminService;
import org.health.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@Controller
@RequestMapping("/doctor")
public class DoctorController {
    private DoctorService doctorService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseDoctor restApiPost(@RequestBody Doctor doctor) {
        return new ResponseDoctor(doctorService.add(doctor));
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Doctor> restApiGetAll() {
        return doctorService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Doctor restApiGetId(@PathVariable("id") long id) {
        return doctorService.getById(id);
    }

    @ApiOperation("Update user")
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Doctor restApiPut(@RequestBody Doctor doctor) {
        return doctorService.update(doctor);
    }

    @ApiOperation("Delete user by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Doctor restApiDelete(@PathVariable("id") long id) {
        return doctorService.delete(id);
    }

    @Autowired
    public void setDoctorService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
}
