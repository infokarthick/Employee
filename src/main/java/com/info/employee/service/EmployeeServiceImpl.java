package com.info.employee.service;

import com.info.employee.model.Employee;
import com.info.employee.model.entity.EmployeeEntity;
import com.info.employee.model.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> fetchEmployees() {
        List<EmployeeEntity> employees = employeeRepository.findAll();

        return employees.stream().map(this::mapEntityToEmployee).toList();
    }

    @Override
    public void createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .department(employee.department()).name(employee.employeeName()).build();
        employeeRepository.save(employeeEntity);
    }

    private Employee mapEntityToEmployee(EmployeeEntity employeeEntity) {
        return new Employee(employeeEntity.getId(), employeeEntity.getName(), employeeEntity.getDepartment());
    }
}
