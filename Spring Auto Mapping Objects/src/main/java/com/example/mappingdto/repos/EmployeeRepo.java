package com.example.mappingdto.repos;

import com.example.mappingdto.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    List<Employee> getManagers();

    List<Employee> findAllByBirthdayBefore(LocalDate toDate);
}
