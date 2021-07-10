package com.example.mappingdto.repos;

import com.example.mappingdto.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    //List<Employee> getManagers();

    //List<Employee> findAllByBirthdayBefore(LocalDate toDate);
}
