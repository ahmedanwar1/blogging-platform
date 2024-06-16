package com.example.blogging_platform.repository;

import com.example.blogging_platform.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Integer> {
}
