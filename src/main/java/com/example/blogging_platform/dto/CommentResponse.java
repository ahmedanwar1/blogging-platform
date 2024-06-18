package com.example.blogging_platform.dto;

import java.time.LocalDateTime;

public record CommentResponse(
        int id,
        String content,
        LocalDateTime createdAt,
        LocalDateTime lastModifiedAt,
        UserResponse user
) {
}
