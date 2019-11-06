package org.health.controller;

import org.health.entity.ResultExamination;
import org.health.service.ResultExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/resultExamination")
public class ResultExaminationController {
    private ResultExaminationService resultExaminationService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResultExamination addResultExamination(@RequestBody ResultExamination resultExamination) {
        return resultExaminationService.addResultExamination(resultExamination);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResultExamination updateResultExamination(@RequestBody ResultExamination resultExamination) {
        return resultExaminationService.updateResultExamination(resultExamination);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResultExamination getHResultExaminationById(@PathVariable("id") long id) {
        return resultExaminationService.getResultExamination(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ResultExamination> getAllResultExaminations() {
        return resultExaminationService.getAllResultExaminations();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResultExamination deleteResultExaminationById(@PathVariable("id") long id) {
        return resultExaminationService.deleteResultExamination(id);
    }

    @Autowired
    public void setResultExaminationService(ResultExaminationService resultExaminationService) {
        this.resultExaminationService = resultExaminationService;
    }
}
