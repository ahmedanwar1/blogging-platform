package com.example.blogging_platform.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ReactionType {
    @JsonProperty("LIKE")
    LIKE,
    @JsonProperty("DISLIKE")
    DISLIKE,
    @JsonProperty("LOVE")
    LOVE,
    @JsonProperty("FUNNY")
    FUNNY,
    @JsonProperty("INFORMATIVE")
    INFORMATIVE,
    @JsonProperty("ANGRY")
    ANGRY,
    @JsonProperty("SAD")
    SAD
}
