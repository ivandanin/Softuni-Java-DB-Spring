package com.example.springdataexercise.controllers;

import com.example.springdataexercise.constants.Constants;
import com.example.springdataexercise.entities.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class ConsoleRunner implements CommandLineRunner {

    private final FileUtil fileUtil;

    @Autowired
    public ConsoleRunner(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public void run(String... args) throws Exception {
       String[] fileContent = this.fileUtil.readFileContent(Constants.CATEGORIES_FILE_PATH);
        System.out.println();
    }
}
