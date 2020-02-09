package org.health.service;

import org.health.dto.SpecialtyDoctorDto;
import org.health.dto.SpecialtyDto;
import org.health.entity.Specialty;

import java.util.List;

public interface SpecialtyService {

    Specialty add(Specialty specialty);

    Specialty checkTitleFree(String title);

    Specialty update(Specialty specialty);

    Specialty delete(long id);

    Specialty getById(long id);

    List<SpecialtyDto> getAllSpecialtyDto();

    SpecialtyDoctorDto getDoctorsByIdSpecialty(long id);
}
