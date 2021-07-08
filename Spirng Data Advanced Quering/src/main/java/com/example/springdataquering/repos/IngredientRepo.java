package com.example.springdataquering.repos;

import com.example.springdataquering.entities.Ingredient;
import com.example.springdataquering.entities.Label;
import com.example.springdataquering.entities.Shampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepo extends JpaRepository<Ingredient, Long> {
    List<Shampoo> findBySizeOrderById(String size);
}
