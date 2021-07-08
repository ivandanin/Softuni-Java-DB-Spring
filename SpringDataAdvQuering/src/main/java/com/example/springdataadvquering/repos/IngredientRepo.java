package com.example.springdataadvquering.repos;

import com.example.springdataadvquering.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepo extends JpaRepository<Ingredient, Long> {

}
