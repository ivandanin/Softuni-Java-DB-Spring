package com.example.mappingdto.entities;

import com.example.mappingdto.entities.Address;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private double salary;

    private Date birthday;

    @ManyToOne
    private Address address;

    @ManyToOne
    @JoinColumn(name = "manager_id"m referencedColumnName = "id")
    private Employee manager;

    @OneToMany(mappedBy = "manager", fetch = FetchType.EAGER)
    private List<Employee> subordinates = new ArrayList<>();
}
