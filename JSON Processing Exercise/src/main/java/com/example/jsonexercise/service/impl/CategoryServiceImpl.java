package com.example.jsonexercise.service.impl;

import com.example.jsonexercise.dtos.CategorySeedDto;
import com.example.jsonexercise.entities.Category;
import com.example.jsonexercise.repos.CategoryRepository;
import com.example.jsonexercise.service.CategoryService;
import com.example.jsonexercise.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper modelMapper;
    private final Gson gson;
    private final CategoryRepository categoryRepository;
    private final ValidationUtil validationUtil;

    public CategoryServiceImpl(ModelMapper modelMapper, Gson gson,
                               CategoryRepository categoryRepository, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.categoryRepository = categoryRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCategories() throws IOException {
        String fileContent = Files.readString
                (Path.of(
                        "09. DB-Advanced-JSON-Processing-Exercises-Resources/09. DB-Advanced-JSON-Processing-Exercises/categories.json"));

        CategorySeedDto[] categorySeedDtos = gson.fromJson(fileContent, CategorySeedDto[].class);

     //   Arrays.stream(categorySeedDtos).filter(validationUtil::isValid)
     //           .map(categorySeedDto -> modelMapper.map(categorySeedDto, Category.class))
     //           .forEach(categoryRepository::save);
    }
}
