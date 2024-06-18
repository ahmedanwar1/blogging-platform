package com.example.blogging_platform.service;

import com.example.blogging_platform.dto.CommentAddingRequest;
import com.example.blogging_platform.dto.CommentResponse;
import com.example.blogging_platform.dto.CommentUpdatingRequest;
import com.example.blogging_platform.exception.CommentNotFoundException;
import com.example.blogging_platform.exception.GeneralDatabaseException;
import com.example.blogging_platform.exception.PostNotFoundException;
import com.example.blogging_platform.mapper.CommentMapper;
import com.example.blogging_platform.model.Comment;
import com.example.blogging_platform.model.Post;
import com.example.blogging_platform.model.User;
import com.example.blogging_platform.repository.CommentRepository;
import com.example.blogging_platform.repository.PostRepository;
import com.example.blogging_platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Transactional
    @Override
    public CommentResponse addComment(int postId, CommentAddingRequest commentAddingRequest) {
        //get auth user
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //fetch user form db
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        // Find the post by ID
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));

        // Create a new comment
        Comment comment = new Comment();
        comment.setContent(commentAddingRequest.content());
        comment.setUser(user);
        comment.setPost(post);

        try {
            // Save the comment
            Comment savedComment = commentRepository.save(comment);

            return commentMapper.INSTANCE.entityToCommentResponseDto(savedComment);

        } catch (Exception e) {
            throw new GeneralDatabaseException("Error saving comment", e);
        }
    }

    @Override
    public List<CommentResponse> getAllCommentsByPost(int postId) {
        List<Comment> commentsEntityList = commentRepository.findAllByPostId(postId);

        return commentsEntityList.stream()
                .map(commentMapper.INSTANCE::entityToCommentResponseDto)
                .toList();
    }

    @Transactional
    @Override
    public CommentResponse updateComment(CommentUpdatingRequest commentUpdatingRequest) {
        Comment comment = commentRepository.findById(commentUpdatingRequest.id())
                .orElseThrow(() -> new CommentNotFoundException("Comment not found"));

        comment.setContent(commentUpdatingRequest.content());

        Comment updatedComment = commentRepository.save(comment);

        return commentMapper.INSTANCE.entityToCommentResponseDto(updatedComment);
    }

    @Transactional
    @Override
    public void deleteComment(int id) {
        if (!commentRepository.existsById(id)) {
            throw new CommentNotFoundException("Comment not found");
        }

        commentRepository.deleteById(id);
    }
}
