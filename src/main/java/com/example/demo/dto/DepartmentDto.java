package com.example.demo.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DepartmentDto extends BaseDto {

    private String name;
    private String description;
    private Date dateOfInformation;
    private int oneOrManyEmployees;
    private List<EmployeeDto> employeeDtoList;

}
