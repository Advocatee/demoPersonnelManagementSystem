package com.example.demo.mapper;


import com.example.demo.dto.DepartmentDto;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeMapper {

    private EmployeeDto toDto(Employee employee) {

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId(employee.getId());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        employeeDto.setFullName(employee.getFullName());
        employeeDto.setDateOfBirth(employee.getDateOfBirth());
        employeeDto.setEmailAddress(employee.getEmailAddress());
        employeeDto.setPosition(employee.getPosition());
        employeeDto.setDateOfEmployment(employee.getDateOfEmployment());

        return null;
    }


    protected List<DepartmentDto> convertEmployeeListToEmployeeDtoList(List<Department> departmentList) {
        if (departmentList == null) {
            return null;
        }

        List<DepartmentDto> departmentDtoList = new ArrayList<>(departmentList.size());
        for (Department dto :
                departmentList) {
//            departmentDtoList.add(dto.get);
        }
        return null;
    }

}
