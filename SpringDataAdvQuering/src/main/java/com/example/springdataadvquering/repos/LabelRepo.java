package com.example.springdataadvquering.repos;

import com.example.springdataadvquering.entities.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepo extends JpaRepository<Label, Long> {
}
