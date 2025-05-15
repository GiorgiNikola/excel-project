package com.excelproject.model;

import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Employee {
    private String name;
    private String surname;
    private String email;
    private String department;
    private double salary;
}
