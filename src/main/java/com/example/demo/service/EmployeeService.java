package com.example.demo.service;

import com.example.demo.model.Employee;

import java.util.UUID;

public interface EmployeeService {

    Employee getEmployeeByUUID(UUID id);

}
