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
    @ApiOperation("Creates new Inspection")
    public Inspection addInspection(@RequestBody Inspection inspection) {
        return inspectionService.addInspection(inspection);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation("Updates inspection")
    public Inspection updateInspection(@RequestBody Inspection inspection) {
        return inspectionService.updateInspection(inspection);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation("Get inspection by id")
    public Inspection getInspection(@ApiParam("Id of inspection to get. Cannot be empty") @PathVariable long id) {
        return inspectionService.getInspection(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation("Get all inspections list")
    public List<Inspection> getAllInspections() {
        return inspectionService.getAllInspections();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation("Deletes inspection by id")
    public Inspection deleteInspection(@ApiParam("Id of inspection to be deleted. Cannot be empty") @PathVariable long id) {
        return inspectionService.deleteInspection(id);
    }

    @Autowired
    public void setInspectionService(InspectionService inspectionService) {
        this.inspectionService = inspectionService;
    }

}
