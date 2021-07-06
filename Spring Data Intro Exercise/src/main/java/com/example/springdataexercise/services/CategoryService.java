package com.example.springdataexercise.services;

import com.example.springdataexercise.entities.Category;

import java.io.IOException;

public interface CategoryService
{
    void seedCategories() throws IOException;

    Category getCategoryById(Long id);
}
