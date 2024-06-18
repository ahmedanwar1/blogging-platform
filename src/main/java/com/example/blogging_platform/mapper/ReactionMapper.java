package com.example.blogging_platform.mapper;

import com.example.blogging_platform.dto.ReactionResponse;
import com.example.blogging_platform.model.Reaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReactionMapper {
    ReactionMapper INSTANCE = Mappers.getMapper(ReactionMapper.class);

    @Mapping(source = "postId", target = "postId")
    @Mapping(source = "userId", target = "userId")
    ReactionResponse entityToReactionResponseDto(int postId, int userId, Reaction reaction);
}
