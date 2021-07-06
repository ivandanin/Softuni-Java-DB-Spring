package com.example.springdataexercise.repos;

import com.example.springdataexercise.entities.Book;
import com.example.springdataexercise.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Long> {
}
