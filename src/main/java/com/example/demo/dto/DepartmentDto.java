package com.example.demo.dto;

import lombok.Data;

import java.util.*;

@Data
public class DepartmentDto extends BaseDto {

    private String name;
    private String description;
    private Date dateOfInformation;
    private List<EmployeeDto> employeeDtoList = new ArrayList<>();

}
