package com.info.employee.service;

import com.info.employee.model.Employee;
import com.info.employee.model.entity.EmployeeEntity;
import com.info.employee.model.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
        when(employeeRepository.findAll())
                .thenReturn(Collections.singletonList(new EmployeeEntity(100L, "Kumar", "Transport")));

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