package com.example.blogging_platform.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PostResponse(
        UserResponse user,
        Integer id,
        String title,
        String content,
        String imageUrl,
        LocalDateTime createdAt,
        LocalDateTime lastModifiedAt,
        CategoryResponse category,
        List<KeywordResponse> keywords
) {
}
