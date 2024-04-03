package com.info.employee.service;

import com.info.employee.model.Address;
import com.info.employee.model.Employee;
import com.info.employee.model.entity.AddressEntity;
import com.info.employee.model.entity.EmployeeEntity;
import com.info.employee.model.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
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
        List<AddressEntity> addressEntities = employee.addressSet().stream()
                .map(address -> mapAddressEntity(address, employeeEntity))
                .toList();
        employeeEntity.setAddressEntities(addressEntities);
        employeeRepository.save(employeeEntity);
    }

    private Employee mapEntityToEmployee(EmployeeEntity employeeEntity) {
        List<AddressEntity> addressEntities = employeeEntity.getAddressEntities();
        Set<Address> addresses = addressEntities.stream().map(this::mapAddress).collect(Collectors.toSet());
        return new Employee(employeeEntity.getId(), employeeEntity.getName(), employeeEntity.getDepartment(), addresses);
    }

    private Address mapAddress(AddressEntity addressEntity) {
        return new Address(addressEntity.getId(), addressEntity.getCity(), addressEntity.getPostalCode());
    }

    private AddressEntity mapAddressEntity(Address address, EmployeeEntity employeeEntity) {
        return AddressEntity.builder().city(address.city()).postalCode(address.postalCode()).employee(employeeEntity).build();
    }
}
