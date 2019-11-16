package org.health.service.impl;

import org.health.dao.EmployeeDao;
import org.health.dto.EmployeeDto;
import org.health.entity.Employee;
import org.health.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao;
    private EmployeeDto employeeDto;

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeDao.addEntity(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeDao.updateEntity(employee);
    }

    @Override
    public EmployeeDto getEmployee(long id) {
        return employeeDto.getEmployeeDto(employeeDao.getEntity(id));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEntities();
    }

    @Override
    public Employee deleteEmployee(long id) {
        return employeeDao.deleteEntity(employeeDao.getEntity(id));
    }

    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Autowired
    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }
}
