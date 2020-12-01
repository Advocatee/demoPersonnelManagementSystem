package com.example.demo.controller;

import com.example.demo.dto.CreateDepartmentRequest;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.service.Impl.DepartmentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class DepartmentController {

    private final Mapper mapper;

    private final DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService, Mapper mapper) {
        this.departmentService = departmentService;
        this.mapper = mapper;
    }

    @GetMapping("/Departments")
    private List<DepartmentDto> getAllDepartmentsWithTheyUsers() {
        List<DepartmentDto> departmentDtoList = mapper.convertDepartmentListToDepartmentDtoList(departmentService.getAll());
        return departmentDtoList;
    }

    @GetMapping("/Departments/{id}")
    public DepartmentDto getDepartmentByUUID(@PathVariable UUID id) {
        Department departmentById = departmentService.getDepartmentById(id);
        return mapper.toDepartmentDto(departmentById);
    }

    @PostMapping("/Departments")
    public DepartmentDto createDepartment(@RequestBody CreateDepartmentRequest createDepartmentRequest) {
        Department department = departmentService.saveDepartment(mapper.fromDtoWithEmployees(createDepartmentRequest));
        DepartmentDto toDepartmentDto = mapper.toDepartmentDto(department);
        return toDepartmentDto;
    }

    @DeleteMapping("/Departments/{id}")
    public void deleteDepartmentDto(@PathVariable UUID id) {
        Department departmentByUUID = departmentService.getDepartmentById(id);
        if (departmentByUUID == null) {
            throw new NullPointerException();
        }
        departmentService.remove(departmentByUUID);
    }

    @DeleteMapping("/Departments/{departmentId}/Employees/{employeeId}")
    public void deleteEmployeeFromDepartment(@PathVariable UUID departmentId, @PathVariable UUID employeeId) {
        departmentService.deleteEmployeeFromDepartment(departmentId, employeeId);
    }
}
