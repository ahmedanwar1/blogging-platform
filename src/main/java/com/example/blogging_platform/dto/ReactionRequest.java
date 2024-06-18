package com.example.blogging_platform.dto;

import com.example.blogging_platform.enums.ReactionType;
import jakarta.validation.constraints.NotNull;

public record ReactionRequest(
        @NotNull
        ReactionType reactionType
) {
}
