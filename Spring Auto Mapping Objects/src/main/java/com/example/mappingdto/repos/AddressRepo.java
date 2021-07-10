package com.example.mappingdto.repos;

import com.example.mappingdto.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
