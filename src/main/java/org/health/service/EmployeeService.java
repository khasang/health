package org.health.service;

import org.health.dto.EmployeeDto;
import org.health.entity.Employee;

import java.util.List;

public interface EmployeeService {
    /**
     * method required for adding employee
     *
     * @param employee - employee for adding
     * @return created employee
     */
    Employee addEmployee(Employee employee);

    /**
     * method required for updating employee
     *
     * @param employee - employee for update
     * @return updated employee
     */
    Employee updateEmployee(Employee employee);

    /**
     * method required for getting employee by id
     *
     * @param id - id employee got getting
     * @return employee by id
     */
    EmployeeDto getEmployee(long id);

    /**
     * method required for getting all employees
     *
     * @return all employees
     */
    List<Employee> getAllEmployees();

    /**
     * method required for deletion employee by id
     *
     * @param id - id employee got delete
     * @return deleted employee by id
     */
    Employee deleteEmployee(long id);
}
