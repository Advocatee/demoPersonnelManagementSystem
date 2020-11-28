package com.example.demo.dto;

import lombok.Data;

import java.util.UUID;

@Data
public abstract class BaseDto {

    private UUID id;
    private String phoneNumber;

}
