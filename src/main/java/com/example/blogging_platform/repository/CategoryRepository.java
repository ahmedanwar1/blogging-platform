package com.example.blogging_platform.repository;

import com.example.blogging_platform.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
