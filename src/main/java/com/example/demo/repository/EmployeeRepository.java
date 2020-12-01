package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    @Query(
            value = "select * from employee left join  employee_department ed on employee.id = ed.employee_id where ed.department_id is null",
            nativeQuery = true)
    List<Employee> getAllByDepartments();

}
