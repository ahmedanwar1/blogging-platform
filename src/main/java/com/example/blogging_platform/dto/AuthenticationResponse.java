package com.example.blogging_platform.dto;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String token
) {
}
