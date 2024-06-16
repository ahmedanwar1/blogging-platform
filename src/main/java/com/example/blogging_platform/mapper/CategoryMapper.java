package com.example.blogging_platform.mapper;

import com.example.blogging_platform.dto.CategoryAddingRequest;
import com.example.blogging_platform.dto.CategoryResponse;
import com.example.blogging_platform.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category categoryAddingRequestToEntity(CategoryAddingRequest categoryAddingRequest);

    CategoryResponse entityToCategoryResponseDto(Category category);
}
