package com.example.mappingdto.service;

import com.example.mappingdto.entities.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    List<Employee> getAllManagers();
    List<Employee> getAllEmployeesBornBefore(LocalDate toDate);
    Employee getEmployeeById(Long id) throws Exception;
    Employee addEmployee(Employee employee);
    Employee updateEmployee(Employee employee) throws Exception;
    Employee deleteEmployee(Long id) throws Exception;
    Long getEmployeeCount();
}
