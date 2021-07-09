package com.example.springadvanced.repos;

import com.example.springadvanced.entities.Ingredient;
import com.example.springadvanced.entities.Label;
import com.example.springadvanced.entities.Shampoo;
import com.example.springadvanced.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ShampooRepo extends JpaRepository<Shampoo, Long> {
    List<Shampoo> findBySizeOrderById(Size size);

    List<Shampoo> findBySizeOrLabel(Size size, Label label);

    List<Shampoo> findByPriceGreaterThanEqual(double price);

    int countByPriceLessThan(double price);

    @Query("SELECT s FROM Shampoo s, IN(s.ingredients) i WHERE i.name IN  :ingredient_names")
    List<Shampoo> findByIngredientsIn(@Param("ingredient_names") Iterable<String> ingredient_names);

    @Query("SELECT s FROM Shampoo s WHERE s.ingredients.size < :count")
    List<Shampoo> findByCountOfIngredientsLessThan(@Param("count") int maxCount);
}
