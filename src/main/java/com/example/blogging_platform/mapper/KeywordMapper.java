package com.example.blogging_platform.mapper;

import com.example.blogging_platform.dto.KeywordAddingRequest;
import com.example.blogging_platform.dto.KeywordResponse;
import com.example.blogging_platform.model.Keyword;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface KeywordMapper {
    KeywordMapper INSTANCE = Mappers.getMapper(KeywordMapper.class);

    Keyword keywordAddingRequestToEntity(KeywordAddingRequest keywordAddingRequest);

    KeywordResponse entityToKeywordResponseDto(Keyword keyword);
}
