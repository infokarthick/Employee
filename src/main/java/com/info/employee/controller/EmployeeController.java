package com.info.employee.controller;

import com.info.employee.model.Employee;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@NoArgsConstructor
@RestController
@RequestMapping("/employee-api")
public class EmployeeController {

    @GetMapping(path = "employees", produces = "application/json")
    public ResponseEntity<Employee> getEmployees() {
        Employee employee = new Employee(1001L, "Karmug", "Engineering");
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
