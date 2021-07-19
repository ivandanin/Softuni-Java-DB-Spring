package com.example.jsonexercise.service.impl;

import com.example.jsonexercise.dtos.CategorySeedDto;
import com.example.jsonexercise.service.CategoryService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final Gson gson;

    public CategoryServiceImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void seedCategories() throws IOException {
        String fileContent = Files.readString
                (Path.of("src/main/resources/09. DB-Advanced-JSON-Processing-Exercises-Resources/09. DB-Advanced-JSON-Processing-Exercises/categories.json"));

        CategorySeedDto[] categorySeedDtos = gson.fromJson(fileContent, CategorySeedDto[].class);
    }
}
