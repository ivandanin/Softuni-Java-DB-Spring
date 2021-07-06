package com.example.springdataexercise.services;

import com.example.springdataexercise.entities.Author;

import java.io.IOException;

public interface AuthorService {
    public void seedAuthors() throws IOException;

    int getAllAuthorsCount();

    Author findAuthorById(Long id);
}
