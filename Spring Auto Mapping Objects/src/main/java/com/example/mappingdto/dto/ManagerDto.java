package com.example.mappingdto.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ManagerDto {
    private String firstName;
    private String lastName;
    private String city;
    private List<EmployeeDto> employees;

    private int getSubordinatesNumber() {
        return employees.size();
    }

}
