package com.example.blogging_platform.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RegisterRequest(
        @NotBlank
        String firstname,
        @NotBlank
        String lastname,
        @Email
        @NotBlank
        String email,
        @Size(min = 8, message = "Password must be at least 8 characters.")
        @NotBlank
        String password
) {
}
