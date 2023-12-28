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

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployee(long id, String name, String email, String department) {
        var employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            var employeeData = employee.get();
            employeeData.setName(name);
            employeeData.setEmail(email);
            employeeData.setDepartment(department);
            return employeeRepository.save(employeeData);
        }
        return null;
    }
}
