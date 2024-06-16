package com.example.blogging_platform.service;

import com.example.blogging_platform.dto.PostAddingRequest;
import com.example.blogging_platform.dto.PostBasicResponse;
import com.example.blogging_platform.dto.PostResponse;
import com.example.blogging_platform.dto.PostsByUserResponse;
import com.example.blogging_platform.exception.GeneralDatabaseException;
import com.example.blogging_platform.exception.PostNotFoundException;
import com.example.blogging_platform.mapper.PostMapper;
import com.example.blogging_platform.model.Category;
import com.example.blogging_platform.model.Keyword;
import com.example.blogging_platform.model.Post;
import com.example.blogging_platform.model.User;
import com.example.blogging_platform.repository.CategoryRepository;
import com.example.blogging_platform.repository.KeywordRepository;
import com.example.blogging_platform.repository.PostRepository;
import com.example.blogging_platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final KeywordRepository keywordRepository;
    private final PostMapper postMapper;

    @Override
    public PostResponse getPostById(int id) {
        Optional<Post> postEntity = postRepository.findById(id);

        if (postEntity.isEmpty()) {
            throw new PostNotFoundException("Post not found.");
        }

        return postMapper.INSTANCE.entityToPostResponseDto(postEntity.get());
    }

    @Override
    public PostsByUserResponse getPostsByUser(String email) {
        List<Post> postsEntityList = postRepository.findAllByUserEmail(email);

        List<PostBasicResponse> postBasicResponseList = postsEntityList.stream()
                .map(postMapper.INSTANCE::entityToPostBasicResponseDto)
                .toList();

        if (postsEntityList.isEmpty()) {
            throw new PostNotFoundException("Cannot find posts for this user");
        }

        User user = postsEntityList.get(0).getUser();
        if (user == null) {
            throw new RuntimeException("User data is not available in the posts.");
        }

        return postMapper.INSTANCE.entityToPostByUserResponseDto(
                user, postBasicResponseList
        );
    }

    @Override
    public PostsByUserResponse getPostsByCurrentUser() {
        //get auth user
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Post> postsEntityList = postRepository.findAllByUserEmail(userDetails.getUsername());

        List<PostBasicResponse> postBasicResponseList = postsEntityList.stream()
                .map(postMapper.INSTANCE::entityToPostBasicResponseDto)
                .toList();

        if (postsEntityList.isEmpty()) {
            throw new PostNotFoundException("Cannot find posts for this user");
        }

        User user = postsEntityList.get(0).getUser();
        if (user == null) {
            throw new RuntimeException("User data is not available in the posts.");
        }

        return postMapper.INSTANCE.entityToPostByUserResponseDto(
                user, postBasicResponseList
        );
    }

    @Override
    @Transactional
    public PostResponse addPost(PostAddingRequest postAddingRequest) {
        //get auth user
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //fetch user form db
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        //fetch category
        Category category = categoryRepository.findById(postAddingRequest.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found."));

        //fetch keywords
        Set<Keyword> keywords = new HashSet<>(keywordRepository.findAllById(postAddingRequest.keywordsSet()));

        //save post
        Post post = postMapper.INSTANCE.postAddingRequestToEntity(postAddingRequest);
        post.setUser(user);
        post.setCategory(category);
        post.setKeywords(keywords);

        try {
            Post savedPost = postRepository.save(post);
            return postMapper.INSTANCE.entityToPostResponseDto(savedPost);

        } catch (Exception e) {
            throw new GeneralDatabaseException("Error saving post", e);
        }
    }
}
