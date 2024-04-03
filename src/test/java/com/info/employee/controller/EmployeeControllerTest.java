package com.info.employee.controller;

import com.info.employee.model.entity.EmployeeEntity;
import com.info.employee.model.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @MockBean
    EmployeeRepository employeeRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getEmployees() throws Exception {
        EmployeeEntity employeeEntity = new EmployeeEntity(1001L, "Karmug", "Engineering");
        when(employeeRepository.findAll()).thenReturn(Collections.singletonList(employeeEntity));

        mockMvc.perform(MockMvcRequestBuilders.get("/employee-api/employees"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}