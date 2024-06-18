package com.example.blogging_platform.controller;

import com.example.blogging_platform.dto.ReactionRequest;
import com.example.blogging_platform.dto.ReactionResponse;
import com.example.blogging_platform.service.ReactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/reactions")
public class ReactionController {

    private final ReactionService reactionService;

    @PostMapping
    public ResponseEntity<ReactionResponse> addReaction(
            @PathVariable int postId,
            @Valid @RequestBody ReactionRequest reactionRequest
    ) {
        ReactionResponse response = reactionService.addReaction(postId, reactionRequest);

        URI location = URI.create(String.format("/posts/%d/reactions/%d", postId, response.id()));

        return ResponseEntity.created(location).body(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> removeReaction(@PathVariable int postId) {
        reactionService.removeReaction(postId);
        return ResponseEntity.noContent().build();
    }
}
