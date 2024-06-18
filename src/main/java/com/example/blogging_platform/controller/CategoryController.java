package com.example.blogging_platform.controller;

import com.example.blogging_platform.dto.CategoryAddingRequest;
import com.example.blogging_platform.dto.CategoryResponse;
import com.example.blogging_platform.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategories() {
        List<CategoryResponse> categories = categoryService.getCategories();

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable int id) {
        CategoryResponse response = categoryService.getCategoryById(id);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> addCategory(
            @Valid @RequestBody CategoryAddingRequest categoryAddingRequest
    ) {
        CategoryResponse response = categoryService.addCategory(categoryAddingRequest);

        URI location = URI.create(String.format("/categories/%d", response.id()));

        return ResponseEntity.created(location).body(response);
    }
}
