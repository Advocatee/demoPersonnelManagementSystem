package com.example.demo.service;

import com.example.demo.model.Department;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {

    List<Department> getAll();

    Department getDepartmentById(UUID id);

    Department saveDepartment(Department department);

    void remove(Department department);

}
