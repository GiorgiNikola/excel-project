package com.excelproject.controller;

import com.excelproject.model.Employee;
import com.excelproject.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/api/employees")
    public Map<String, Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/api/department-average-salary")
    public Map<String, Double> getDepartmentAverageSalary() {
        return employeeService.getDepartmentsAverageSalary();
    }
}
