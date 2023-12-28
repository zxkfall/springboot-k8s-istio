package com.flywinter.demo.controller;

import com.flywinter.demo.entity.Employee;
import com.flywinter.demo.service.IEmployeeService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/delete")
    public void deleteEmployee(@RequestParam long id){
        employeeService.deleteEmployee(id);
    }

    @PatchMapping("/update")
    public Employee updateEmployee(@RequestParam long id, @RequestParam String name, @RequestParam String email, @RequestParam String department){
        return employeeService.updateEmployee(id, name, email, department);
    }
}
