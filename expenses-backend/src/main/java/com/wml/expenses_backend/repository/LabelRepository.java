package com.wml.expenses_backend.repository;

import com.wml.expenses_backend.Entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LabelRepository extends JpaRepository<Label, Long> {
    Optional<Label> findByLabelName(String labelName);
}