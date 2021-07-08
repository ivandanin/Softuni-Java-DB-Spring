package com.example.springdataquering.init;

import com.example.springdataquering.repos.IngredientRepo;
import com.example.springdataquering.repos.LabelRepo;
import com.example.springdataquering.repos.ShampooRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppController implements CommandLineRunner {

    private final ShampooRepo shampooRepo;
    private final LabelRepo labelRepo;
    private final IngredientRepo ingredientRepo;

    @Autowired
    public AppController(ShampooRepo shampooRepo, LabelRepo labelRepo, IngredientRepo ingredientRepo) {
        this.shampooRepo = shampooRepo;
        this.labelRepo = labelRepo;
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
