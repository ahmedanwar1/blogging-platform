package com.example.blogging_platform.dto;

import jakarta.validation.constraints.NotBlank;

public record CommentAddingRequest(
        @NotBlank
        String content
) {
}
