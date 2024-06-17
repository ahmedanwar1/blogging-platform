package com.example.blogging_platform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentUpdatingRequest(
        @NotNull
        Integer id,
        @NotBlank
        String content
) {
}
