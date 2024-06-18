package com.example.blogging_platform.dto;

import jakarta.validation.constraints.NotBlank;

public record UserResponse(
        String firstname,
        String lastname,
        String email
) {
}
