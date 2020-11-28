package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    private UUID id;
    private String phoneNumber;

}
