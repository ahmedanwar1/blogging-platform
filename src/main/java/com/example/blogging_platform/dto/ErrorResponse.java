package com.example.blogging_platform.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        int statusCode,
        String message,
        LocalDateTime timestamp,
        List<String> details
) {
}
