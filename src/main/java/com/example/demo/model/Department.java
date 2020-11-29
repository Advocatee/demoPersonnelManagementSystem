package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Department extends BaseEntity {

    private String name;
    private String description;
    private Date dateOfInformation;

    @ManyToMany(mappedBy = "departments", fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateOfInformation=" + dateOfInformation +
                '}';
    }
}
