package com.example.blogging_platform.repository;

import com.example.blogging_platform.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("SELECT p FROM Post p WHERE p.user.email = :email")
    List<Post> findAllByUserEmail(@Param("email") String email);
}
