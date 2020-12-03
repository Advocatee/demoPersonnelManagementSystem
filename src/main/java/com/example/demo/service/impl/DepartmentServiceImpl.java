package com.example.demo.service.impl;

import com.example.demo.exception.DepartmentNotFound;
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
        List<Employee> employees = department.getEmployees();
        List<Employee> savedEmployees = new ArrayList<>();
        if (CollectionUtils.isEmpty(employees)) {
            throw new DepartmentNotFound();
        }
        for (Employee employee : employees
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
    public void removeDepartmentById(UUID uuid) {
        departmentRepository.deleteById(uuid);
    }

    @Override
    public void deleteEmployeeFromDepartment(UUID departmentId, UUID employeeId) {
        Department department = getDepartmentById(departmentId);
        List<Employee> employeeList = department.getEmployees();
        for (Employee employee : employeeList
        ) {
            if (employee.getId().equals(employeeId)) {
                employee.getDepartments().removeIf(department1 -> department.getId().equals(departmentId));
                employeeRepository.save(employee);
            }
        }
        departmentRepository.save(department);
    }

    @Override
    public void addEmployeeToDepartment(UUID departmentId, UUID employeeId) {
        Department departmentById = departmentRepository.findById(departmentId).orElseThrow(NullPointerException::new);
        Employee employeeById = employeeRepository.findById(employeeId).orElseThrow(NullPointerException::new);
        if (departmentById == null) {
            throw new NullPointerException();
        } else {
            departmentById.getEmployees().add(employeeById);
            departmentRepository.save(departmentById);
            employeeById.getDepartments().add(departmentById);
            employeeRepository.save(employeeById);
        }
    }
}
