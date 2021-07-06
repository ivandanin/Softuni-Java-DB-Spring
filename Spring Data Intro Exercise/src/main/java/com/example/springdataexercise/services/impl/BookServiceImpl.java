package com.example.springdataexercise.services.impl;

import com.example.springdataexercise.entities.Author;
import com.example.springdataexercise.entities.EditionType;
import com.example.springdataexercise.entities.utils.FileUtil;
import com.example.springdataexercise.repos.BookRepo;
import com.example.springdataexercise.services.AuthorService;
import com.example.springdataexercise.services.BookService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import static com.example.springdataexercise.constants.Constants.BOOKS_FILE_PATH;

@Service
public class BookServiceImpl implements BookService {

    private final AuthorService authorService;
    private final BookRepo bookRepo;
    private final FileUtil fileUtil;

    public BookServiceImpl(BookRepo bookRepo, FileUtil fileUtil) {
        this.bookRepo = bookRepo;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepo.count() != 0) {
            return;
        }

        String[] fileContent = this.fileUtil.readFileContent(BOOKS_FILE_PATH);

        Arrays.stream(fileContent).forEach(r -> {
            String[] params = r.split("\\]s+");

            Author author = this.getRandomAuthor();

            EditionType editionType = EditionType.values()[Integer.parseInt(params[0])];
        });
    }

    private Author getRandomAuthor() {
        Random random = new Random();
        int randomId = random.nextInt(this.authorService.getAllAuthorsCount()) + 1;

        return this.authorService.findAuthorById((long) randomId);
    }
}
