package com.example.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CreateDepartmentRequest {

    private String phoneNumber;
    private String name;
    private String description;
    private Date dateOfInformation;
    private List<CreateShortEmployeeRequest> employeeDtoList = new ArrayList<>();
}
