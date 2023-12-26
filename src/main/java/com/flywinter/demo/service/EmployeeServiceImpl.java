package com.flywinter.demo.service;

import com.flywinter.demo.entity.Employee;
import com.flywinter.demo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee addEmployee(String name, String email, String department) {
        return employeeRepository.save(new Employee(name, email, department));
    }

    @Override
    public List<Employee> getAllEmployees() {
        var employees = new ArrayList<Employee>();
        employeeRepository.findAll().forEach(employees::add);
        return employees;
    }
}
