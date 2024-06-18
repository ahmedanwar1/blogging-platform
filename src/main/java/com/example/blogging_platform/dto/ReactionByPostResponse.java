package com.example.blogging_platform.dto;

import com.example.blogging_platform.enums.ReactionType;

import java.time.LocalDateTime;

public record ReactionByPostResponse(
        int id,
        UserResponse user,
        ReactionType reactionType,
        LocalDateTime createdAt
) {
}
