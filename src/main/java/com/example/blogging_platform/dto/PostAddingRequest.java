package com.example.blogging_platform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record PostAddingRequest(
        @NotBlank
        String title,
        @NotBlank
        String content,
        @NotBlank
        String imageUrl,

        @NotNull
        Integer categoryId,

        Set<Integer> keywordsSet
) {
}
