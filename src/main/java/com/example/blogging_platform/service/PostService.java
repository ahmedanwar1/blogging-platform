package com.example.blogging_platform.service;

import com.example.blogging_platform.dto.PostAddingRequest;
import com.example.blogging_platform.dto.PostResponse;
import com.example.blogging_platform.dto.PostsByUserResponse;

import java.util.List;

public interface PostService {
    PostResponse getPostById(int id);

    PostsByUserResponse getPostsByUser(String email);

    PostsByUserResponse getPostsByCurrentUser();

    PostResponse addPost(PostAddingRequest postAddingRequest);
}
