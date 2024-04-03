package com.info.employee.controller;

import com.info.employee.model.Employee;
import com.info.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @MockBean
    EmployeeService employeeService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getEmployees() throws Exception {
        Employee employee = new Employee(1001L, "Karmug", "Engineering");
        when(employeeService.fetchEmployees()).thenReturn(Collections.singletonList(employee));

        mockMvc.perform(MockMvcRequestBuilders.get("/employee-api/employees"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void addEmployee() throws Exception {
        Employee employee = new Employee(1001L, "Karmug", "Engineering");
        doNothing().when(employeeService).createEmployee(isA(Employee.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/employee-api/employee")
                        .contentType("application/json").content("{\"employeeName\":\"Mugil\",\"department\":\"Cooking\"}"))
                .andExpect(status().isCreated())
                .andDo(print());
    }
}