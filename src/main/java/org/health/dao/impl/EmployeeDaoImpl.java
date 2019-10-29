package org.health.dao.impl;

import org.health.dao.EmployeeDao;
import org.health.entity.Employee;

public class EmployeeDaoImpl extends BasicDaoImpl<Employee> implements EmployeeDao {
    public EmployeeDaoImpl(Class<Employee> entityClass) {
        super(entityClass);
    }
}
