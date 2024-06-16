package com.example.blogging_platform.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryAddingRequest(
        @NotBlank
        String name,
        @NotBlank
        String description
) {
}
