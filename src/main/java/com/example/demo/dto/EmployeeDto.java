package com.example.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeDto extends BaseDto {

    private String fullName;
    private Date dateOfBirth;
    private String emailAddress;
    private String position;
    private Date dateOfEmployment;

}
