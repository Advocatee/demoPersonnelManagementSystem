package com.example.demo.dto;

import lombok.Data;
import java.util.Date;
import java.util.UUID;

@Data
public class CreateEmployeeRequest {

    private String phoneNumber;
    private String fullName;
    private Date dateOfBirth;
    private String emailAddress;
    private String position;
    private Date dateOfEmployment;
    private UUID departmentId;

}
