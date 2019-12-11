package org.health.dto;

import org.health.entity.Car;
import org.health.entity.Schedule;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleDto {
    private long id;
    private String name;
    private String title;
    private List<CarDto> carList = new ArrayList<>();

    public ScheduleDto getScheduleDto(Schedule schedule) {
        List<CarDto> carDtos = new ArrayList<>();
        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setId(schedule.getId());
        scheduleDto.setName(schedule.getName());


        //getCarDtoFromCar(schedule, carDtos);
        scheduleDto.setCarList(carDtos);
        return scheduleDto;
    }

//    private void getCarDtoFromCar(Schedule schedule, List<CarDto> carDtos) {
//        for (Car car : schedule.getCarList()) {
//            CarDto carDto = new CarDto();
//            carDto.setId(car.getId());
//            carDto.setModel(car.getModel());
//            carDto.setYear(car.getYear());
//
//            carDtos.add(carDto);
//        }
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CarDto> getCarList() {
        return carList;
    }

    public void setCarList(List<CarDto> carList) {
        this.carList = carList;
    }
}
