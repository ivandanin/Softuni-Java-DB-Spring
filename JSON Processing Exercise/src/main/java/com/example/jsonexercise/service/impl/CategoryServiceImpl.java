package com.example.jsonexercise.service.impl;

import com.example.jsonexercise.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public void seedCategories() throws IOException {
        String fileContent = Files.readString
                (Path.of("09. DB-Advanced-JSON-Processing-Exercises-Resources/" +
                        "09. DB-Advanced-JSON-Processing-Exercises/categories.json"));
    }
}
