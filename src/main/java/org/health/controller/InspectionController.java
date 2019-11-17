package org.health.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api
public class InspectionController {
    private InspectionService inspectionService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "Creates new Inspection", response = Inspection.class)
    public Inspection addInspection(@RequestBody Inspection inspection) {
        return inspectionService.addInspection(inspection);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "Updates inspection", response = Inspection.class)
    public Inspection updateInspection(@RequestBody Inspection inspection) {
        return inspectionService.updateInspection(inspection);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "Get inspection by id", notes = "Provide an id to get specific inspection", response = Inspection.class)
    public Inspection getInspection(@ApiParam(value = "Id of inspection to get. Cannot be empty", required = true) @PathVariable long id) {
        return inspectionService.getInspection(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "Get all inspections list", response = Inspection.class, responseContainer = "List")
    public List<Inspection> getAllInspections() {
        return inspectionService.getAllInspections();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "Deletes inspection by id", notes = "Provide an id to delete specific inspection", response = Inspection.class)
    public Inspection deleteInspection(@ApiParam(value = "Id of inspection to be deleted. Cannot be empty", required = true) @PathVariable long id) {
        return inspectionService.deleteInspection(id);
    }

    @Autowired
    public void setInspectionService(InspectionService inspectionService) {
        this.inspectionService = inspectionService;
    }
}
