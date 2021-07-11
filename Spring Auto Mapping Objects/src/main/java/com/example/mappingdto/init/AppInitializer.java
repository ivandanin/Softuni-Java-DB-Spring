package com.example.mappingdto.init;

import com.example.mappingdto.dto.EmployeeDto;
import com.example.mappingdto.dto.ManagerDto;
import com.example.mappingdto.entities.Address;
import com.example.mappingdto.entities.Employee;
import com.example.mappingdto.service.AddressService;
import com.example.mappingdto.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppInitializer implements CommandLineRunner {

    private final EmployeeService employeeService;

    private final AddressService addressService;

    public AppInitializer(EmployeeService employeeService, AddressService addressService) {
        this.employeeService = employeeService;
        this.addressService = addressService;
    }

    @Override
    public void run(String... args) {
        ModelMapper mapper = new ModelMapper();

        // 1.Simple mapping
        Address address1 = new Address("Bulgaria", "Sofia", "Al Malinov 3");
        address1 = addressService.addAddress(address1);
        Employee employee1 = new Employee("Ivan", "Danin", 16000,
                LocalDate.of(2001, 9, 23), address1);
        employee1 = employeeService.addEmployee(employee1);

        EmployeeDto employeeDto = mapper.map(employee1, EmployeeDto.class);
        System.out.printf("EmployeeDto: %s%n", employeeDto);

        // 2.Advanced mapping
        List<Address> addresses = List.of(
                new Address("Bulgaria", "Sofia", "G.S.Rakovski, 50"),
                new Address("Bulgaria", "Sofia", "Dondukov 3"),
                new Address("Bulgaria", "Sofia", "Hr. Smirnenski"),
                new Address("Bulgaria", "Sofia", "Slivnitsa 13"),
                new Address("Bulgaria", "Sofia", "Angel Kanchev 33"),
                new Address("Bulgaria", "Sofia", "Angel Kanchev 35")
        );
        addresses = addresses.stream().map(addressService::addAddress).collect(Collectors.toList());

        List<Employee> employees = List.of(
                new Employee("Steve", "Adams", 5780, LocalDate.of(1982, 3, 12),
                        addresses.get(0)),
                new Employee("Stephen", "Petrov", 2760, LocalDate.of(1974, 5, 19),
                                addresses.get(1)),
                new Employee("Hristina", "Petrova", 3680, LocalDate.of(1991, 11, 9),
                                addresses.get(1)),
                new Employee("Diana", "Atanasova", 6790, LocalDate.of(1989, 12, 9),
                                addresses.get(2)),
                new Employee("Samuil", "Georgiev", 4780, LocalDate.of(1979, 2, 10),
                                addresses.get(3)),
                new Employee("Slavi", "Hristov", 3780, LocalDate.of(1985, 2, 23),
                                addresses.get(4)),
                new Employee("Georgi", "Miladinov", 3960, LocalDate.of(1995, 3, 11),
                                addresses.get(5))
        );
        List<Employee> created = employees.stream().map(employeeService::addEmployee).collect(Collectors.toList());

        created.get(1).setManager(created.get(0));
        created.get(2).setManager(created.get(0));

        created.get(4).setManager(created.get(3));
        created.get(5).setManager(created.get(3));
        created.get(6).setManager(created.get(3));

        List<Employee> updated = created.stream().map(employeeService::updateEmployee).collect(Collectors.toList());

        TypeMap<Employee, ManagerDto> managerTypeMap = mapper.createTypeMap(Employee.class, ManagerDto.class)
                .addMappings(m -> {
                    m.map(Employee::getSubordinates, ManagerDto::setEmployees);
                    m.map(src -> src.getAddress().getCity(), ManagerDto::setCity);
                });
        mapper.getTypeMap(Employee.class, EmployeeDto.class)
                .addMapping(src -> src.getAddress(), EmployeeDto::setAddressCity);
        mapper.validate();

        List<Employee> managers = employeeService.getAllManagers();
        List<ManagerDto> managerDtos = managers.stream().map(managerTypeMap::map).collect(Collectors.toList());

        // 3. Projection

        TypeMap employeeMap = mapper.getTypeMap(Employee.class, EmployeeDto.class)
                .addMapping(src -> src.getManager().getLastName(), EmployeeDto::setLastName);

        List<Employee> employeeBornBefore = employeeService
                .getAllEmployeesBornBefore(LocalDate.of(1990, 1, 1));
        employeeBornBefore.stream().map(employeeMap::map).forEach(System.out::println);
    }
}
