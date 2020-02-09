package org.health.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.health.dto.RoleUserDto;
import org.health.dto.SpecialtyDoctorDto;
import org.health.dto.SpecialtyDto;
import org.health.dto.response.ResponseSpecialty;
import org.health.entity.Specialty;
import org.health.entity.userdb.extend.Admin;
import org.health.entity.userdb.extend.Doctor;
import org.health.service.DoctorService;
import org.health.service.SpecialtyService;
import org.health.service.impl.SpecialtyServiceImpl;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@Controller
@RequestMapping("/specialty")
public class SpecialtyController {
    private SpecialtyService specialtyService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Specialty restApiPost(@RequestBody Specialty specialty) {
        return specialtyService.add(specialty);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity restApiGetAll() {
        Response<List<SpecialtyDto>> response = new Response<>();

        try {
            response.setData(specialtyService.getAllSpecialtyDto());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/doctor/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity getDoctorsByIdSpecialty(@PathVariable("id") long id) {
        Response<SpecialtyDoctorDto> response = new Response<>();

        try {
            response.setData(specialtyService.getDoctorsByIdSpecialty(id));
            return ResponseEntity.ok(response);

        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseSpecialty restApiGetId(@PathVariable("id") long id) {
        return new ResponseSpecialty(specialtyService.getById(id));
    }

    @ApiOperation("Update user")
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Specialty restApiPut(@RequestBody Specialty specialty) {
        return specialtyService.update(specialty);
    }

    @ApiOperation("Delete user by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Specialty restApiDelete(@PathVariable("id") long id) {
        return specialtyService.delete(id);
    }

    @Autowired
    public void setSpecialtyService(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }
}
