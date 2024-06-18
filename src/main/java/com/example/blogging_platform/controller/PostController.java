package com.example.blogging_platform.controller;

import com.example.blogging_platform.dto.PostAddingRequest;
import com.example.blogging_platform.dto.PostResponse;
import com.example.blogging_platform.dto.PostsByUserResponse;
import com.example.blogging_platform.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/current")
    public ResponseEntity<PostsByUserResponse> getPostByCurrentUser() {
        PostsByUserResponse response = postService.getPostsByCurrentUser();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PostsByUserResponse> getPostByEmail(@RequestParam String email) {
        PostsByUserResponse response = postService.getPostsByUser(email);

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable int id) {
        PostResponse response = postService.getPostById(id);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PostResponse> addPost(@Valid @RequestBody PostAddingRequest postAddingRequest) {
        PostResponse response = postService.addPost(postAddingRequest);

        URI location = URI.create(String.format("/posts/%d", response.id()));

        return ResponseEntity.created(location).body(response);
    }
}
