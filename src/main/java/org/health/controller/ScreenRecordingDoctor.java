package org.health.controller;

import org.health.dto.response.ResponseSpecialty;
import org.health.dto.response.ResponseSpecialtyAll;
import org.health.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/screen-recording-doctor")
public class ScreenRecordingDoctor {
    private SpecialtyService specialtyService;

//    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseSpecialtyAll restApiGetAll() {
//        return new ResponseSpecialtyAll(specialtyService.getAllSpecialtyDto());
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseSpecialty restApiGetById(@PathVariable("id") long id) {
//        return new ResponseSpecialty(specialtyService.getById(id));
//    }
//
//    @Autowired
//    public void setSpecialtyService(SpecialtyService specialtyService) {
//        this.specialtyService = specialtyService;
//    }
}
