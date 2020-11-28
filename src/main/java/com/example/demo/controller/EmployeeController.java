package com.example.demo.controller;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.mapper.Mapper;
import com.example.demo.service.Impl.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    private final Mapper mapper;

    public EmployeeController(EmployeeServiceImpl employeeService, Mapper mapper) {
        this.employeeService = employeeService;
        this.mapper = mapper;
    }

    @GetMapping("/allEmployee")
    public List<EmployeeDto> getAllEmployee() {
        List<EmployeeDto> employeeDtoList = mapper.convertEmployeeListToEmployeeDtoList(employeeService.getAll());
        return employeeDtoList;
    }


}
