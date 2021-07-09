package com.example.springadvanced.repos;

import com.example.springadvanced.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsRepo extends JpaRepository<Ingredient, Long> {
}
