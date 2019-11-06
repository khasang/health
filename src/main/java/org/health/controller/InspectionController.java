package org.health.controller;

import org.health.entity.Inspection;
import org.health.service.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Inspection Controller.
 *
 * @author v.kupriyanov
 * @version 1
 * @since 06.11.2019
 */

@Controller
@RequestMapping("/inspection")
public class InspectionController {
    private InspectionService inspectionService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Inspection addInspection(@RequestBody Inspection inspection) {
        return inspectionService.addInspection(inspection);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Inspection updateInspection(@RequestBody Inspection inspection) {
        return inspectionService.updateInspection(inspection);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Inspection getInspection(@PathVariable long id) {
        return inspectionService.getInspection(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Inspection> getAllInspections() {
        return inspectionService.getAllInspections();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Inspection deleteInspection(@PathVariable long id) {
        return inspectionService.deleteInspection(id);
    }

    @Autowired
    public void setInspectionService(InspectionService inspectionService) {
        this.inspectionService = inspectionService;
    }

}
