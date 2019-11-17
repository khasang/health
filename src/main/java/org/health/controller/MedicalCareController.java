package org.health.controller;

import java.util.*;

import io.swagger.annotations.*;
import org.health.entity.*;
import org.health.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Api
@Controller
@RequestMapping("/medicalCare")
public class MedicalCareController {
    private MedicalCareService medicalCareService;

    @ApiOperation("Add medical care")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public MedicalCare addMedicalCare(@RequestBody MedicalCare medicalCare) {
        return medicalCareService.addMedicalCare(medicalCare);
    }

    @ApiOperation("Update medical care")
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public MedicalCare updateMedicalCare(@RequestBody MedicalCare medicalCare) {
        return medicalCareService.updateMedicalCare(medicalCare);
    }

    @ApiOperation("Get medical care by id")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public MedicalCare getMedicalCareById(@PathVariable("id") long id) {
        return medicalCareService.getMedicalCare(id);
    }

    @ApiOperation("Get all medical cares")
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<MedicalCare> getAllMedicalCares() {
        return medicalCareService.getAllMedicalCares();
    }

    @ApiOperation("Delete medical care by id")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public MedicalCare deleteMedicalCareById(@PathVariable("id") long id) {
        return medicalCareService.deleteMedicalCare(id);
    }

    @Autowired
    public void setMedicalCareService(MedicalCareService medicalCareService) {
        this.medicalCareService = medicalCareService;
    }
}
