package com.example.springdataexercise.repos;

import com.example.springdataexercise.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
