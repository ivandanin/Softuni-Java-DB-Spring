package com.example.jsonexercise.init;

import com.example.jsonexercise.service.CategoryService;
import org.springframework.boot.CommandLineRunner;

public class CommandRunner implements CommandLineRunner {
    private final CategoryService categoryService;

    public CommandRunner(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {

    }

    private void seedData() {
        categoryService.seedCategories();
    }
}
