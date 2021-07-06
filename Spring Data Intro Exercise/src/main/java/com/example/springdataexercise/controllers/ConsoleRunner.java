package com.example.springdataexercise.controllers;
import com.example.springdataexercise.services.AuthorService;
import com.example.springdataexercise.services.BookService;
import com.example.springdataexercise.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class ConsoleRunner implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public ConsoleRunner(CategoryService categoryService,
                         AuthorService authorService,
                         BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();
    }
}
