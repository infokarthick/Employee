package com.info.employee.model.repository;

import com.info.employee.model.entity.EmployeeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void testWhenNoRecordFoundInEmployeeTableThenEmployeeRepositoryShouldReturnEmpty() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();

        assertThat(employeeEntityList.size()).isEqualTo(3);
    }

    @Test
    void testEmployeeRepositoryShouldPersistEmployeeRecordInTable() {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("kuar");
        employeeEntity.setDepartment("civil");

        employeeRepository.saveAndFlush(employeeEntity);

        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        assertThat(employeeEntityList.size()).isEqualTo(4);
    }
}