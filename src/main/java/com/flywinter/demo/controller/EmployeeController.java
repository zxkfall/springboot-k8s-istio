package com.flywinter.demo.controller;

import com.flywinter.demo.entity.Employee;
import com.flywinter.demo.service.IEmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final IEmployeeService employeeService;

    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/all")
    public List<Employee> getEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String name, @RequestParam String email, @RequestParam String department){
        return employeeService.addEmployee(name, email, department);
    }
}
