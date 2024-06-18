package com.example.blogging_platform.dto;

import com.example.blogging_platform.enums.ReactionType;

import java.time.LocalDateTime;

public record ReactionResponse(
        int id,
        int postId,
        int userId,
        ReactionType reactionType,
        LocalDateTime createdAt
) {
}
