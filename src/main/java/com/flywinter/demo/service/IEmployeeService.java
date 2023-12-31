package com.flywinter.demo.service;

import com.flywinter.demo.entity.Employee;

import java.util.List;

public interface IEmployeeService {

    Employee addEmployee(String name, String email, String department);

    List<Employee> getAllEmployees();

    void deleteEmployee(long id);

    Employee updateEmployee(long id, String name, String email, String department);
}
