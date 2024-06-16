package com.example.blogging_platform.mapper;

import com.example.blogging_platform.dto.PostAddingRequest;
import com.example.blogging_platform.dto.PostBasicResponse;
import com.example.blogging_platform.dto.PostResponse;
import com.example.blogging_platform.dto.PostsByUserResponse;
import com.example.blogging_platform.model.Post;
import com.example.blogging_platform.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    Post postAddingRequestToEntity(PostAddingRequest postAddingRequest);

    PostResponse entityToPostResponseDto(Post post);

    PostBasicResponse entityToPostBasicResponseDto(Post post);

    @Mapping(source = "user", target = "user")
    @Mapping(source = "posts", target = "posts")
    PostsByUserResponse entityToPostByUserResponseDto(User user, List<PostBasicResponse> posts);
}
