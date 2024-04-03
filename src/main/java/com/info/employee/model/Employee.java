package com.info.employee.model;

import java.util.Set;

public record Employee(long employeeId, String employeeName, String department, Set<Address> addressSet) {
}
