package com.example.blogging_platform.service;

import com.example.blogging_platform.dto.CategoryAddingRequest;
import com.example.blogging_platform.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getCategories();

    CategoryResponse getCategoryById(int id);

    CategoryResponse addCategory(CategoryAddingRequest categoryAddingRequest);


}
