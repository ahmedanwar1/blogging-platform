package com.example.blogging_platform.dto;

import java.util.List;

public record PostsByUserResponse(
        UserResponse user,
        List<PostBasicResponse> posts
) {
}
