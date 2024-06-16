package com.example.blogging_platform.service;

import com.example.blogging_platform.dto.CategoryAddingRequest;
import com.example.blogging_platform.dto.CategoryResponse;
import com.example.blogging_platform.exception.CategoryNotFoundException;
import com.example.blogging_platform.exception.GeneralDatabaseException;
import com.example.blogging_platform.mapper.CategoryMapper;
import com.example.blogging_platform.model.Category;
import com.example.blogging_platform.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> getCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(categoryMapper.INSTANCE::entityToCategoryResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getCategoryById(int id) {
        Optional<Category> categoryEntity = categoryRepository.findById(id);

        if (categoryEntity.isEmpty()) {
            throw new CategoryNotFoundException("Category with id: " + id + " not found.");
        }

        return categoryMapper.entityToCategoryResponseDto(categoryEntity.get());
    }

    @Override
    public CategoryResponse addCategory(CategoryAddingRequest categoryAddingRequest) {
        try {
            Category categoryEntity = categoryMapper.INSTANCE.categoryAddingRequestToEntity(categoryAddingRequest);

            Category savedCategory = categoryRepository.save(categoryEntity);

            return categoryMapper.INSTANCE.entityToCategoryResponseDto(savedCategory);
        } catch (Exception e) {
            throw new GeneralDatabaseException("Error saving category", e);
        }
    }
}
