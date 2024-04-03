package com.info.employee.controller;

import com.info.employee.model.Employee;
import com.info.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/employee-api")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(path = "employees", produces = "application/json")
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employees = employeeService.fetchEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
