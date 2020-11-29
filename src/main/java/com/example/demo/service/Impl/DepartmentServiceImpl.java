package com.example.demo.service.Impl;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final EmployeeRepository employeeRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(UUID id) {
        return departmentRepository.getOne(id);
    }

    @Override
    public Department saveDepartment(Department department) throws NullPointerException {
        List<Employee> savedEmployees = new ArrayList<>();
        if (CollectionUtils.isEmpty(department.getEmployees())) {
            throw new NullPointerException();
        }
        for (Employee employee : department.getEmployees()
        ) {
            if (employee != null) {
                employee.getDepartments().add(department);
                savedEmployees.add(employeeRepository.save(employee));
            }
        }
        department.setEmployees(savedEmployees);
        return departmentRepository.save(department);
    }

    @Override
    public void remove(Department department) {
        departmentRepository.delete(department);
    }
}
