package com.example.blogging_platform.repository;

import com.example.blogging_platform.model.Post;
import com.example.blogging_platform.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReactionRepository extends JpaRepository<Reaction, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Reaction r where r.post.id = :postId AND r.user.id = :userId")
    void deleteByPostIdAndUserId(@Param("postId") int postId, @Param("userId") int userId);
}
