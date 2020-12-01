package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Employee extends BaseEntity {

    private String fullName;
    private Date dateOfBirth;
    private String emailAddress;
    private String position;
    private Date dateOfEmployment;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "EMPLOYEE_DEPARTMENT", joinColumns = {@JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "id")}
            , inverseJoinColumns = {@JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "id")})
    private List<Department> departments = new ArrayList<>();

    @Override
    public String toString() {
        return "Employee{" +
                "fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", emailAddress='" + emailAddress + '\'' +
                ", position='" + position + '\'' +
                ", dateOfEmployment=" + dateOfEmployment +
                ", departments=" + departments +
                '}';
    }
}
