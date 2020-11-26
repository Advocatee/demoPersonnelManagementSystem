package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Department extends BaseEntity {

    private String name;
    private String description;
    private Date dateOfInformation;
    private int oneOrManyEmployees;

    @ManyToMany(mappedBy = "departments", fetch = FetchType.LAZY)
    private List<Employee> employees;

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateOfInformation=" + dateOfInformation +
                ", oneOrManyEmployees=" + oneOrManyEmployees +
                '}';
    }
}