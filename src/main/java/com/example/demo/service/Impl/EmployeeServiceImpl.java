package com.example.demo.service.Impl;

import com.example.demo.exception.DepartmentNotFound;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }


    @Override
    public Employee getEmployeeByUUID(UUID id) {
        return employeeRepository.getOne(id);
    }

    @Override
    public Employee saveEmployee(Employee employee, UUID departmentId) {
        if (departmentId != null) {
            departmentRepository.findById(departmentId)
                    .ifPresentOrElse((department -> {
                        List<Department> depList = new ArrayList<>();
                        depList.add(department);
                        employee.setDepartments(depList);
                    }), () -> {
                        throw new DepartmentNotFound();
                    });

        }
        return employeeRepository.save(employee);
    }

    @Override
    public void remove(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public List<Employee> getAllEmployeeWitchDontBelongToAnyDepartment() {
        return employeeRepository.getAllByDepartments();
    }
}
