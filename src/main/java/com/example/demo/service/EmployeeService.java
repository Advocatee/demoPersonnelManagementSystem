package com.example.demo.service;

import com.example.demo.model.Employee;
import javassist.tools.web.BadHttpRequest;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    Employee getEmployeeByUUID(UUID id);

    Employee saveEmployee(Employee employee, UUID departmentId) throws BadHttpRequest;

    void removeEmployeeById(UUID uuid);

    List<Employee> getAllEmployeeWitchDontBelongToAnyDepartment();

}
