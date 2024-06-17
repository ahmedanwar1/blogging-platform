package com.example.blogging_platform.mapper;

import com.example.blogging_platform.dto.CommentAddingRequest;
import com.example.blogging_platform.dto.CommentResponse;
import com.example.blogging_platform.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentResponse entityToCommentResponseDto(Comment comment);
}
