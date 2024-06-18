package com.example.blogging_platform.service;

import com.example.blogging_platform.dto.ReactionRequest;
import com.example.blogging_platform.dto.ReactionResponse;

public interface ReactionService {
    ReactionResponse addReaction(int postId, ReactionRequest reactionRequest);

    void removeReaction(int postId);
}
