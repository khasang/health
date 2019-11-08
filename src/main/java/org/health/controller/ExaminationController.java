package org.health.controller;

import org.health.entity.Examination;
import org.health.entity.ResultExamination;
import org.health.service.ExaminationService;
import org.health.service.ResultExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/examination")
public class ExaminationController {
    private ExaminationService examinationService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Examination addResultExamination(@RequestBody Examination examination) {
        return examinationService.addExamination(examination);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Examination updateResultExamination(@RequestBody Examination examination) {
        return examinationService.updateExamination(examination);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Examination getHResultExaminationById(@PathVariable("id") long id) {
        return examinationService.getExaminationById(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Examination> getAllResultExaminations() {
        return examinationService.getAllExaminations();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Examination deleteResultExaminationById(@PathVariable("id") long id) {
        return examinationService.deleteExaminationById(id);
    }

    @Autowired
    public void setExaminationService(ExaminationService examinationService) {
        this.examinationService = examinationService;
    }
}
