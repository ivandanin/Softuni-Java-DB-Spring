package com.example.mappingdto.service;

import com.example.mappingdto.entities.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    List<Employee> getAllManagers();
    List<Employee> getAllEmployeesBornBefore(LocalDate toDate);
    Employee getEmployeeById(Long id) ;
    Employee addEmployee(Employee employee);
    Employee updateEmployee(Employee employee) ;
    Employee deleteEmployee(Long id) throws Exception;
    Long getEmployeeCount();
}
