package com.example.springdataadvquering.repos;

import com.example.springdataadvquering.entities.Shampoo;
import com.example.springdataadvquering.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShampooRepo extends JpaRepository<Shampoo, Long> {
    List<Shampoo> findBySizeOrderById(Size size);
}
