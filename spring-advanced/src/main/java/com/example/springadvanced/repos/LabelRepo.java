package com.example.springadvanced.repos;

import com.example.springadvanced.entities.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabelRepo extends JpaRepository<Label, Long> {
    List<Label> findByTitle(String title);
}
