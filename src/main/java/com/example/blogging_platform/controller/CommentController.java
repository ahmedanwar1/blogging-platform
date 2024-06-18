package com.example.blogging_platform.controller;

import com.example.blogging_platform.dto.CommentAddingRequest;
import com.example.blogging_platform.dto.CommentResponse;
import com.example.blogging_platform.dto.CommentUpdatingRequest;
import com.example.blogging_platform.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentResponse>> getAllCommentsByPost(@PathVariable int postId) {
        List<CommentResponse> response = commentService.getAllCommentsByPost(postId);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CommentResponse> addComment(
            @PathVariable int postId,
            @Valid @RequestBody CommentAddingRequest commentAddingRequest
    ) {
        CommentResponse response = commentService.addComment(postId, commentAddingRequest);

        URI location = URI.create(String.format("/comments/%d", response.id()));

        return ResponseEntity.created(location).body(response);
    }

    @PatchMapping
    public ResponseEntity<CommentResponse> updateComment(
            @Valid @RequestBody CommentUpdatingRequest commentUpdatingRequest
    ) {
        CommentResponse response = commentService.updateComment(commentUpdatingRequest);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable int commentId) {
        commentService.deleteComment(commentId);

        return ResponseEntity.noContent().build();
    }
}
