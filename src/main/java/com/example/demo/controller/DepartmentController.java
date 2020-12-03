package com.example.demo.controller;

import com.example.demo.dto.CreateDepartmentRequest;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.Department;
import com.example.demo.service.impl.DepartmentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/departments")
    private ResponseEntity<List<DepartmentDto>> getAllDepartmentsWithTheyUsers() {
        return ResponseEntity.ok(mapper.convertDepartmentListToDepartmentDtoList(departmentService.getAll()));
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentByUUID(@PathVariable UUID id) {
        Department departmentById = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(mapper.toDepartmentDto(departmentById));
    }

    @PostMapping("/departments")
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody CreateDepartmentRequest createDepartmentRequest) {
        Department department = departmentService.saveDepartment(mapper.fromDtoWithEmployees(createDepartmentRequest));
        return ResponseEntity.ok(mapper.toDepartmentDto(department));
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<Void> deleteDepartmentDto(@PathVariable UUID id) {
        departmentService.removeDepartmentById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/departments/{departmentId}/employees/{employeeId}")
    public ResponseEntity<Void> deleteEmployeeFromDepartment(@PathVariable UUID departmentId, @PathVariable UUID employeeId) {
        departmentService.deleteEmployeeFromDepartment(departmentId, employeeId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/departments/{departmentId}/employees/{employeeId}")
    public ResponseEntity<Void> addEmployeeToDepartment(@PathVariable UUID departmentId, @PathVariable UUID employeeId) {
        departmentService.addEmployeeToDepartment(departmentId, employeeId);
        return ResponseEntity.ok().build();
    }

}
