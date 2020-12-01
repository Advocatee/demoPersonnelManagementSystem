package com.example.demo.controller;

import com.example.demo.dto.CreateEmployeeRequest;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.exception.DepartmentNotFound;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.Employee;
import com.example.demo.service.Impl.EmployeeServiceImpl;
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

    @GetMapping("/Employees/{id}")
    public EmployeeDto getEmployeeByUUID(@PathVariable UUID id) {
        Employee employeeById = employeeService.getEmployeeByUUID(id);
        return mapper.toEmployee(employeeById);
    }

    @PostMapping("/Employees")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest) {
        EmployeeDto employeeDto = null;
        try {
            Employee employee = employeeService.saveEmployee(mapper.toEmployee(createEmployeeRequest), createEmployeeRequest.getDepartmentId());
            employeeDto = mapper.toEmployee(employee);
        } catch (DepartmentNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping("/Employees/{id}")
    public void deleteEmployeeDto(@PathVariable UUID id) {
        Employee employeeByUUID = employeeService.getEmployeeByUUID(id);
        if (employeeByUUID == null) {
            throw new NullPointerException();
        }
        employeeService.remove(employeeByUUID);
    }

    @GetMapping("/Employees")
    public List<EmployeeDto> getAllEmployeeWitchDontBelongToAnyDepartment() {
        return mapper.convertEmployeeListToEmployeeDtoList(employeeService.getAllEmployeeWitchDontBelongToAnyDepartment());
    }

}
