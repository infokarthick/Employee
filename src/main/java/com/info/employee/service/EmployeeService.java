package com.info.employee.service;

import com.info.employee.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> fetchEmployees();

    void createEmployee(Employee employee);
}
