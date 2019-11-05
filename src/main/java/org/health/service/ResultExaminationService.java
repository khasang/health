package org.health.service;

import org.health.entity.ResultExamination;

import java.util.List;

public interface ResultExaminationService {
    /**
     * method required for adding resultExamination
     *
     * @param resultExamination - resultExamination for adding
     * @return created resultExamination
     */
    ResultExamination addResultExamination(ResultExamination resultExamination);

    /**
     * method required for updating resultExamination
     *
     * @param resultExamination - resultExamination for update
     * @return updated resultExamination
     */
    ResultExamination updateResultExamination(ResultExamination resultExamination);

    /**
     * method required for getting resultExamination by id
     *
     * @param id - id resultExamination got getting
     * @return resultExamination by id
     */
    ResultExamination getResultExamination(long id);

    /**
     * method required for getting all resultExaminations
     *
     * @return all resultExaminations
     */
    List<ResultExamination> getAllResultExaminations();

    /**
     * method required for deletion resultExamination by id
     *
     * @param id - id resultExamination got delete
     * @return deleted resultExamination by id
     */
    ResultExamination deleteResultExamination(long id);
}
