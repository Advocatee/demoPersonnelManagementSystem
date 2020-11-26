package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.Impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @GetMapping( value = "/allEmployee")
    public List<Employee> testSomething() {
        return employeeService.getAll();
    }

}
