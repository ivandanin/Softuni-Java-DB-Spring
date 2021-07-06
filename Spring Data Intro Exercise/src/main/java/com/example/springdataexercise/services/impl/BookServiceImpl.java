package com.example.springdataexercise.services.impl;

import com.example.springdataexercise.entities.*;
import com.example.springdataexercise.entities.utils.FileUtil;
import com.example.springdataexercise.repos.BookRepo;
import com.example.springdataexercise.services.AuthorService;
import com.example.springdataexercise.services.BookService;
import com.example.springdataexercise.services.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static com.example.springdataexercise.constants.Constants.BOOKS_FILE_PATH;

@Service
public class BookServiceImpl implements BookService {

    private final AuthorService authorService;
    private final BookRepo bookRepo;
    private final FileUtil fileUtil;
    private final CategoryService categoryService;

    public BookServiceImpl(AuthorService authorService, BookRepo bookRepo,
                           FileUtil fileUtil, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookRepo = bookRepo;
        this.fileUtil = fileUtil;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepo.count() != 0) {
            return;
        }

        String[] fileContent = this.fileUtil.readFileContent(BOOKS_FILE_PATH);

        Arrays.stream(fileContent)
                .forEach(r -> {
                    String[] params = r.split("\\s+");

            Author author = this.getRandomAuthor();

            EditionType editionType = EditionType.values()[Integer.parseInt(params[0])];

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

            LocalDate releaseDate = LocalDate.parse(params[1], formatter);
            int copies = Integer.parseInt(params[2]);
            BigDecimal price = new BigDecimal(params[3]);
            AgeRestriction ageRestriction = AgeRestriction
                    .values()[Integer.parseInt(params[4])];
            String title = this.getTitle(params);

            Set<Category> categories = this.getRandomCategories();

            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);
            book.setCategories(categories);

            bookRepo.saveAndFlush(book);

        });
    }

    private Set<Category> getRandomCategories() {
        Set<Category> result = new HashSet<>();
        Random random = new Random();
        int bound = random.nextInt(3) + 1;

        for (int i = 1; i <= bound; i++) {
            int categoryId = random.nextInt(8) + 1;
            result.add(this.categoryService.getCategoryById((long) categoryId));
        }
        return result;
    }

    private String getTitle(String[] params) {
        StringBuilder sb = new StringBuilder();
        for (int i = 5; i < params.length; i++) {
            sb.append(params[i]).append(" ");
        }
        return sb.toString().trim();
    }

    private Author getRandomAuthor() {
        Random random = new Random();
        int randomId = random.nextInt(this.authorService.getAllAuthorsCount()) + 1;

        return this.authorService.findAuthorById((long) randomId);
    }
}
