package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        // printAllBooksAfterYear(2000);
        // printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
        // printAllAuthorsAndNumberOfTheirBooks();
        // pritnALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");
        // printAllBooksByAgeRestriction(AgeRestriction.TEEN);
        //printBooksNotReleasedInYear(LocalDate.ofEpochDay(1998));

    }
    private void printBooksNotReleasedInYear(LocalDate year){
        bookService.findAllByReleaseDateNot(year).forEach(book ->
                System.out.printf("%s%n", book.getTitle()));
    }

    private void printBooksWithPrice() {
        bookService.findAllByPriceLessThanAndPriceGreaterThan(new BigDecimal(5), new BigDecimal(40))
                .forEach(book -> System.out.printf("%s - %.2f%n", book.getTitle(), book.getPrice()));

    }

    private void printGoldenTitles() {
        bookService.findAllByCopiesLessThan(5000).forEach(book ->
                System.out.printf("%s%n", book.getTitle()));
    }

    private void printAllBooksByAgeRestriction(AgeRestriction restriction) {
        bookService.findAllByAgeRestriction(restriction)
                .forEach(book ->
                        System.out.printf("%s%n", book.getTitle()));
    }

    private void pritnALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
