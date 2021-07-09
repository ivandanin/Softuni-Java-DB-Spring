package com.example.springadvanced.repos;

import com.example.springadvanced.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientsRepo extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByNameStartsWith(String letter);

    List<Ingredient> findByNameIn(List<String> names);

}
