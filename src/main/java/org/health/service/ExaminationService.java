package org.health.service;

import org.health.entity.Examination;

import java.util.List;

public interface ExaminationService {
    /**
     * method required for adding Examination
     *
     * @param examination - Examination for adding
     * @return created Examination
     */
    Examination addExamination(Examination examination);

    /**
     * method required for updating Examination
     *
     * @param examination - Examination for update
     * @return updated Examination
     */
    Examination updateExamination(Examination examination);

    /**
     * method required for getting Examination by id
     *
     * @param id - id Examination got getting
     * @return Examination by id
     */
    Examination getExaminationById(long id);

    /**
     * method required for getting all Examinations
     *
     * @return all Examinations
     */
    List<Examination> getAllExaminations();

    /**
     * method required for deletion Examinations by id
     *
     * @param id - id Examination got delete
     * @return deleted Examination by id
     */
    Examination deleteExaminationById(long id);
}
