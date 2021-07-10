package com.example.mappingdto.service.impl;

import com.example.mappingdto.entities.Employee;
import com.example.mappingdto.repos.EmployeeRepo;
import com.example.mappingdto.service.EmployeeService;

import java.time.LocalDate;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepo employeeRepo;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public List<Employee> getAllManagers() {
        return employeeRepo.getManagers();
    }

    @Override
    public List<Employee> getAllEmployeesBornBefore(LocalDate toDate) {
        return employeeRepo.findAllByBirthdayBefore(toDate);
    }

    @Override
    public Employee getEmployeeById(Long id) throws Exception {
        return employeeRepo.findById(id).orElseThrow(() ->
                new Exception(String.format("Employee with ID = %s does not exists.", id)));
    }

    @Override
    public Employee addEmployee(Employee employee) {
        employee.setId(null);
        if (employee.getManager() != null) {
            employee.getManager().getSubordinates().add(employee);
        }
        return employeeRepo.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) throws Exception {
        Employee existing = getEmployeeById(employee.getId());
        Employee updated = employeeRepo.save(employee);
        if (existing.getManager() != null &&
                !existing.getManager().equals(employee.getManager())) {
            existing.getManager().getSubordinates().add(updated);
        }
        return updated;
    }

    @Override
    public Employee deleteEmployee(Long id) throws Exception {
        Employee removed = getEmployeeById(id);
        if (removed.getManager() != null) {
            removed.getManager().getSubordinates().remove(removed);
        }
        employeeRepo.deleteById(id);
        return removed;
    }

    @Override
    public Long getEmployeeCount() {
        return employeeRepo.count();
    }
}
