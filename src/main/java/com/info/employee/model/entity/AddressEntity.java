package com.info.employee.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "ADDRESS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    public String city;

    @Column(name = "postal_code")
    public Integer postalCode;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    public EmployeeEntity employee;
}
