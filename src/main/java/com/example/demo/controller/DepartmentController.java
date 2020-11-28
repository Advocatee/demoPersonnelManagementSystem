package com.example.demo.controller;

import com.example.demo.dto.CreateDepartmentRequest;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.Department;
import com.example.demo.service.Impl.DepartmentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    private final Mapper mapper;

    private final DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService, Mapper mapper) {
        this.departmentService = departmentService;

        this.mapper = mapper;
    }

    @GetMapping("/allDepWithUsers")
    private List<DepartmentDto> getAllDepartmentsWithTheyUsers() {
        List<DepartmentDto> departmentDtoList = mapper.convertDepartmentListToDepartmentDtoList(departmentService.getAll());
        return departmentDtoList;
    }

//    @GetMapping(value = "/department/{id}")
//    public DepartmentDto getOneDepartment(@PathVariable UUID id) {
//        return departmentMapper.getDepartment(id);
//    }

    @PostMapping("/createDepartment")
    public DepartmentDto createDepartment(@RequestBody CreateDepartmentRequest createDepartmentRequest) {
        Department department = departmentService.saveDepartment(mapper.toDepartmentFromCreateDepartmentRequest(createDepartmentRequest));
        DepartmentDto toDepartmentDto = mapper.toDepartmentDto(department);
        return toDepartmentDto;
    }

}
