package org.health.dao.impl;

import org.health.dao.InspectionDao;
import org.health.entity.Inspection;

/**
 * Inspection Dao Implementation.
 *
 * @author v.kupriyanov
 * @version 1
 * @since 06.11.2019
 */
public class InspectionDaoImpl extends BasicDaoImpl<Inspection> implements InspectionDao {
    public InspectionDaoImpl(Class<Inspection> classEntity) {
        super(classEntity);
    }
}
