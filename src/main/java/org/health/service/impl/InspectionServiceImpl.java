package org.health.service.impl;

import org.health.dao.InspectionDao;
import org.health.entity.Inspection;
import org.health.service.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Inspection Service Implementation.
 *
 * @author v.kupriyanov
 * @version 1
 * @since 06.11.2019
 */

@Service("inspectionService")
public class InspectionServiceImpl implements InspectionService {
    private InspectionDao inspectionDao;

    @Override
    public Inspection addInspection(Inspection inspection) {
        return inspectionDao.addEntity(inspection);
    }

    @Override
    public Inspection updateInspection(Inspection inspection) {
        return inspectionDao.updateEntity(inspection);
    }

    @Override
    public Inspection getInspection(long id) {
        return inspectionDao.getEntity(id);
    }

    @Override
    public List<Inspection> getAllInspections() {
        return inspectionDao.getAllEntities();
    }

    @Override
    public Inspection deleteInspection(long id) {
        return inspectionDao.deleteEntity(getInspection(id));
    }

    @Autowired
    public void setInspectionDao(InspectionDao inspectionDao) {
        this.inspectionDao = inspectionDao;
    }
}
