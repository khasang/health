package org.health.service;

import java.util.*;

import org.health.entity.*;

public interface MedicalCareService {
    /**
     * method required for adding medical care
     *
     * @param medicalCare - medical care for adding
     * @return created medical care
     */
    MedicalCare addMedicalCare(MedicalCare medicalCare);

    /**
     * method required for updating medical care
     *
     * @param medicalCare - medical care for update
     * @return updated medical care
     */
    MedicalCare updateMedicalCare(MedicalCare medicalCare);

    /**
     * method required for getting medical care by id
     *
     * @param id - id medical care got getting
     * @return medical care by id
     */
    MedicalCare getMedicalCare(long id);

    /**
     * method required for getting all medical cares
     *
     * @return all medical cares
     */
    List<MedicalCare> getAllMedicalCares();

    /**
     * method required for deletion medical care by id
     *
     * @param id - id medical care got delete
     * @return deleted medical care by id
     */
    MedicalCare deleteMedicalCare(long id);
}
