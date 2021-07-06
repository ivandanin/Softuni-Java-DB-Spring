package com.example.springdataexercise.services.impl;

import com.example.springdataexercise.entities.Category;
import com.example.springdataexercise.entities.utils.FileUtil;
import com.example.springdataexercise.repos.CategoryRepo;
import com.example.springdataexercise.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

import static com.example.springdataexercise.constants.Constants.CATEGORIES_FILE_PATH;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final FileUtil fileUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo, FileUtil fileUtil) {
        this.categoryRepo = categoryRepo;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCategories() throws IOException {

        if (this.categoryRepo.count() != 0) {
            return;
        }

        String[] fileContent = this.fileUtil.readFileContent(CATEGORIES_FILE_PATH);

        Arrays.stream(fileContent).forEach(r -> {
            Category category = new Category(r);

            this.categoryRepo.saveAndFlush(category);
        });
    }
}
