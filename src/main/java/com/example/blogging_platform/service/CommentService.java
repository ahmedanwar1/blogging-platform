package com.example.blogging_platform.service;

import com.example.blogging_platform.dto.CommentAddingRequest;
import com.example.blogging_platform.dto.CommentResponse;
import com.example.blogging_platform.dto.CommentUpdatingRequest;

import java.util.List;

public interface CommentService {
    CommentResponse addComment(int postId, CommentAddingRequest commentAddingRequest);

    List<CommentResponse> getAllCommentsByPost(int postId);

    CommentResponse updateComment(CommentUpdatingRequest commentUpdatingRequest);

    void deleteComment(int id);

}
