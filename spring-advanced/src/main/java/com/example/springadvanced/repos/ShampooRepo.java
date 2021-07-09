package com.example.springadvanced.repos;

import com.example.springadvanced.entities.Shampoo;
import com.example.springadvanced.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShampooRepo extends JpaRepository<Shampoo, Long> {
    List<Shampoo> findBySizeOrderById(Size size);
}
