package com.example.blogging_platform.service;

import com.example.blogging_platform.dto.KeywordAddingRequest;
import com.example.blogging_platform.dto.KeywordResponse;

import java.util.List;

public interface KeywordService {
    KeywordResponse getKeywordById(int id);

    List<KeywordResponse> getKeywords();

    List<KeywordResponse> addListOfKeywords(List<KeywordAddingRequest> requestList);

    KeywordResponse addKeyword(KeywordAddingRequest keywordAddingRequest);
}
