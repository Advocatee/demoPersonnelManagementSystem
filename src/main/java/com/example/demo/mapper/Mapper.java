package com.example.demo.mapper;

import com.example.demo.dto.*;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Data
@Component
public class Mapper {

    public DepartmentDto toDepartmentDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setPhoneNumber(department.getPhoneNumber());
        departmentDto.setName(department.getName());
        departmentDto.setDescription(department.getDescription());
        departmentDto.setDateOfInformation(department.getDateOfInformation());
        departmentDto.setEmployeeDtoList(convertEmployeeListToEmployeeDtoList(department.getEmployees()));
        return departmentDto;
    }

    public List<DepartmentDto> convertDepartmentListToDepartmentDtoList(List<Department> departmentList) {
        if (departmentList == null) {
            return null;
        }
        List<DepartmentDto> departmentDtoList = new ArrayList<>(departmentList.size());
        for (Department dto :
                departmentList) {
            departmentDtoList.add(toDepartmentDto(dto));
        }
        return departmentDtoList;
    }

    public List<Department> convertDepartmentDtoListToDepartmentList(List<DepartmentDto> departmentDtoList) {
        if (departmentDtoList == null) {
            return null;
        }
        List<Department> departmentList = new ArrayList<>(departmentDtoList.size());
        for (DepartmentDto dto : departmentDtoList
        ) {
            departmentList.add(toDepartment(dto));
        }
        return departmentList;
    }

    public Department toDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setId(departmentDto.getId());
        department.setPhoneNumber(departmentDto.getPhoneNumber());
        department.setName(departmentDto.getName());
        department.setDescription(departmentDto.getDescription());
        department.setDateOfInformation(departmentDto.getDateOfInformation());
        department.setEmployees(convertEmployeeDtoListToEmployeeList(departmentDto.getEmployeeDtoList()));
        return department;
    }

    public List<EmployeeDto> convertEmployeeListToEmployeeDtoList(List<Employee> employeeList) {
        if (employeeList == null) {
            return null;
        }
        List<EmployeeDto> employeeDtoList = new ArrayList<>(employeeList.size());
        for (Employee employee :
                employeeList) {
            employeeDtoList.add(toEmployee(employee));
        }
        return employeeDtoList;
    }

    public Employee toEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setFullName(employeeDto.getFullName());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setEmailAddress(employeeDto.getEmailAddress());
        employee.setPosition(employeeDto.getPosition());
        employee.setDateOfEmployment(employeeDto.getDateOfEmployment());
        return employee;
    }

    public List<Employee> convertEmployeeDtoListToEmployeeList(List<EmployeeDto> employeeDtoList) {
        if (employeeDtoList == null) {
            return null;
        }
        List<Employee> employeeList = new ArrayList<>(employeeDtoList.size());
        for (EmployeeDto dto : employeeDtoList
        ) {
            employeeList.add(toEmployee(dto));
        }
        return employeeList;
    }

    public EmployeeDto toEmployee(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        employeeDto.setFullName(employee.getFullName());
        employeeDto.setDateOfBirth(employee.getDateOfBirth());
        employeeDto.setEmailAddress(employee.getEmailAddress());
        employeeDto.setPosition(employee.getPosition());
        employeeDto.setDateOfEmployment(employee.getDateOfEmployment());
        return employeeDto;
    }

    public Department fromDtoWithEmployees(CreateDepartmentRequest createDepartmentRequest) {
        Department department = new Department();
        department.setPhoneNumber(createDepartmentRequest.getPhoneNumber());
        department.setName(createDepartmentRequest.getName());
        department.setDescription(createDepartmentRequest.getDescription());
        department.setDateOfInformation(createDepartmentRequest.getDateOfInformation());
        department.setEmployees(convertToEmployeeList(createDepartmentRequest.getEmployeeDtoList()));
        return department;
    }

    public List<Employee> convertToEmployeeList(List<CreateShortEmployeeRequest> employeeRequests) {
        if (employeeRequests == null) {
            return null;
        }
        List<Employee> employeeList = new ArrayList<>(employeeRequests.size());
        for (CreateShortEmployeeRequest dto : employeeRequests
        ) {
            employeeList.add(toEmployee(dto));
        }
        return employeeList;
    }

    public Employee toEmployee(CreateShortEmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setPhoneNumber(employeeRequest.getPhoneNumber());
        employee.setFullName(employeeRequest.getFullName());
        employee.setDateOfBirth(employeeRequest.getDateOfBirth());
        employee.setEmailAddress(employeeRequest.getEmailAddress());
        employee.setPosition(employeeRequest.getPosition());
        employee.setDateOfEmployment(employeeRequest.getDateOfEmployment());
        return employee;
    }

    public Employee toEmployee(CreateEmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setPhoneNumber(employeeRequest.getPhoneNumber());
        employee.setFullName(employeeRequest.getFullName());
        employee.setDateOfBirth(employeeRequest.getDateOfBirth());
        employee.setEmailAddress(employeeRequest.getEmailAddress());
        employee.setPosition(employeeRequest.getPosition());
        employee.setDateOfEmployment(employeeRequest.getDateOfEmployment());
        return employee;
    }


}
