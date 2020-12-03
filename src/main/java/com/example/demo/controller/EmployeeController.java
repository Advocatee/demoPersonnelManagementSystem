package com.example.demo.controller;

import com.example.demo.dto.CreateEmployeeRequest;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.exception.DepartmentNotFound;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.Employee;
import com.example.demo.service.impl.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    private final Mapper mapper;

    public EmployeeController(EmployeeServiceImpl employeeService, Mapper mapper) {
        this.employeeService = employeeService;
        this.mapper = mapper;
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeByUUID(@PathVariable UUID id) {
        Employee employeeById = employeeService.getEmployeeByUUID(id);
        return ResponseEntity.ok(mapper.toEmployee(employeeById));
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest) {
        try {
            EmployeeDto employeeDto = null;
            Employee employee = employeeService.saveEmployee(mapper.toEmployee(createEmployeeRequest), createEmployeeRequest.getDepartmentId());
            employeeDto = mapper.toEmployee(employee);
            return ResponseEntity.ok(employeeDto);
        } catch (DepartmentNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployeeDto(@PathVariable UUID id) {
        employeeService.removeEmployeeById(id);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployeeWitchDontBelongToAnyDepartment() {
        return ResponseEntity.ok(mapper.convertEmployeeListToEmployeeDtoList(employeeService.getAllEmployeeWitchDontBelongToAnyDepartment()));
    }

}
