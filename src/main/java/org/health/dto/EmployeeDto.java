package org.health.dto;

import org.health.entity.Car;
import org.health.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeDto {
    private long id;
    private String name;
    private String title;
    private List<CarDto> carList = new ArrayList<>();

    public EmployeeDto getEmployeeDto(Employee employee) {
        List<CarDto> carDtos = new ArrayList<>();
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setTitle(employee.getTitle());

        getCarDtoFromCar(employee, carDtos);
        employeeDto.setCarList(carDtos);
        return employeeDto;
    }

    private void getCarDtoFromCar(Employee employee, List<CarDto> carDtos) {
        for (Car car : employee.getCarList()) {
            CarDto carDto = new CarDto();
            carDto.setId(car.getId());
            carDto.setModel(car.getModel());
            carDto.setYear(car.getYear());

            carDtos.add(carDto);
        }
    }

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
