package com.example.blogging_platform.controller;

import com.example.blogging_platform.dto.KeywordAddingRequest;
import com.example.blogging_platform.dto.KeywordResponse;
import com.example.blogging_platform.service.KeywordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/keywords")
public class KeywordController {

    private final KeywordService keywordService;

    @GetMapping
    public ResponseEntity<List<KeywordResponse>> getKeywords() {
        List<KeywordResponse> keywords = keywordService.getKeywords();

        return ResponseEntity.ok(keywords);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KeywordResponse> getKeywordById(@PathVariable int id) {
        KeywordResponse response = keywordService.getKeywordById(id);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<KeywordResponse> addKeyword(@Valid @RequestBody KeywordAddingRequest keywordAddingRequest) {
        KeywordResponse response = keywordService.addKeyword(keywordAddingRequest);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<KeywordResponse>> addListOfKeywords(@Valid @RequestBody List<KeywordAddingRequest> keywordAddingRequest) {

        List<KeywordResponse> response = keywordService.addListOfKeywords(keywordAddingRequest);

        return ResponseEntity.ok(response);
    }
}
