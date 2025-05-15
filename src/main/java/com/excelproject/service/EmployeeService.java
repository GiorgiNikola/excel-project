package com.excelproject.service;

import com.excelproject.model.Employee;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Getter
public class EmployeeService {

    Map<String, Employee> employees;

    public EmployeeService() {
        employees = new HashMap<>();
    }

    public Map<String,Double> getDepartmentsAverageSalary() {
        return employees.values()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
    }

}
