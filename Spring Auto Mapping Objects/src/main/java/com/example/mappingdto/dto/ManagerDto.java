package com.example.mappingdto.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ManagerDto {
    private String firstName;
    private String lastName;
    private List<EmployeeDto> subordinates;

    private int getSubordinatesNumber() {
        return subordinates.size();
    }
}
