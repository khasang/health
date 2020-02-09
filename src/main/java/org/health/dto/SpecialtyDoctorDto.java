package org.health.dto;

import org.health.entity.Specialty;
import org.health.entity.userdb.extend.Doctor;

import java.util.LinkedList;
import java.util.List;

public class SpecialtyDoctorDto {
    private SpecialtyDto specialtyDto;
    private List<DoctorDto> doctorDtos = new LinkedList<>();

    public SpecialtyDoctorDto(Specialty specialty, List<Doctor> doctors) {
        if (doctors != null) {
            specialtyDto = new SpecialtyDto(specialty, doctors.size());
            doctors.forEach(d -> doctorDtos.add(new DoctorDto(d)));
            doctorDtos.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));

        } else {
            specialtyDto = new SpecialtyDto(specialty, 0);
        }
    }

    public SpecialtyDto getSpecialtyDto() {
        return specialtyDto;
    }

    public List<DoctorDto> getDoctorDtos() {
        return doctorDtos;
    }

    @Override
    public String toString() {
        return "SpecialtyDoctorDto{" +
                "specialtyDto=" + specialtyDto +
                ", doctorDtos=" + doctorDtos +
                '}';
    }
}
