package com.info.employee.service;

import com.info.employee.model.Employee;
import com.info.employee.model.entity.AddressEntity;
import com.info.employee.model.entity.EmployeeEntity;
import com.info.employee.model.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class EmployeeServiceTest {

    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void fetchEmployees() {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(333L);
        employeeEntity.setName("joe");
        employeeEntity.setDepartment("ijop");
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(22L);
        addressEntity.setCity("Bru");
        addressEntity.setPostalCode(1050);
        addressEntity.setEmployee(employeeEntity);
        List<AddressEntity> addressEntitySet = new ArrayList<>();
        addressEntitySet.add(addressEntity);
        employeeEntity.setAddressEntities(addressEntitySet);
        when(employeeRepository.findAll())
                .thenReturn(Collections.singletonList(employeeEntity));

        List<Employee> employeeList = employeeService.fetchEmployees();

        assertThat(employeeList.size()).isOne();
    }

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @MockBean
        public EmployeeRepository employeeRepository;

        @Bean
        public EmployeeServiceImpl employeeService() {
            return new EmployeeServiceImpl(employeeRepository);
        }
    }
}