package com.info.employee.controller;

import com.info.employee.model.Address;
import com.info.employee.model.Employee;
import com.info.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
        Set<Address> addressSet = new HashSet<>();
        addressSet.add(new Address(11L, "Brussels", 1040));
        Employee employee = new Employee(1001L, "Karmug", "Engineering", addressSet);
        when(employeeService.fetchEmployees()).thenReturn(Collections.singletonList(employee));

        mockMvc.perform(MockMvcRequestBuilders.get("/employee-api/employees"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void addEmployee() throws Exception {
        String employeeRequest = "{\"employeeId\":1,\"employeeName\":\"Lokesh\",\"department\":\"electrical\",\"addressSet\":[{\"addressId\":1,\"city\":\"Brussels\",\"postalCode\":1050},{\"addressId\":2,\"city\":\"Antwerb\",\"postalCode\":2502}]}";
        doNothing().when(employeeService).createEmployee(isA(Employee.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/employee-api/employee")
                        .contentType("application/json").content(employeeRequest))
                .andExpect(status().isCreated())
                .andDo(print());
    }
}