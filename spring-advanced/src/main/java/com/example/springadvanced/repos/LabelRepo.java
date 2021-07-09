package com.example.springadvanced.repos;

import com.example.springadvanced.entities.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepo extends JpaRepository<Label, Long> {
}
