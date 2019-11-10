package org.health.service;

import org.health.entity.Inspection;

import java.util.List;

/**
 * Inspection Service.
 *
 * @author v.kupriyanov
 * @version 1
 * @since 06.11.2019
 */
public interface InspectionService {
    /**
     * Method required for adding inspection.
     *
     * @param inspection - inspection for adding
     * @return created inspection
     */
    Inspection addInspection(Inspection inspection);

    /**
     * Method required for updating inspection.
     *
     * @param inspection - inspection for update
     * @return updated inspection
     */
    Inspection updateInspection(Inspection inspection);

    /**
     * Method required for getting inspection by id.
     *
     * @param id - id inspection to get
     * @return inspection by id
     */
    Inspection getInspection(long id);

    /**
     * Method required for getting all inspections.
     *
     * @return all inspections
     */
    List<Inspection> getAllInspections();

    /**
     * Method required for deletion inspection by id.
     *
     * @param id - id inspection got delete
     * @return deleted inspection by id
     */
    Inspection deleteInspection(long id);

}
