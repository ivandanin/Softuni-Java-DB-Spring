package com.example.springdataquering.repos;

import com.example.springdataquering.entities.Shampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShampooRepo extends JpaRepository<Shampoo, Long> {
    List<Shampoo> findByBrand(String brand);
}
