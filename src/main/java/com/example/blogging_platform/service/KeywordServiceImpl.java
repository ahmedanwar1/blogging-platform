package com.example.blogging_platform.service;

import com.example.blogging_platform.dto.KeywordAddingRequest;
import com.example.blogging_platform.dto.KeywordResponse;
import com.example.blogging_platform.exception.GeneralDatabaseException;
import com.example.blogging_platform.exception.KeywordNotFoundException;
import com.example.blogging_platform.mapper.KeywordMapper;
import com.example.blogging_platform.model.Keyword;
import com.example.blogging_platform.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KeywordServiceImpl implements KeywordService {

    private final KeywordRepository keywordRepository;
    private final KeywordMapper keywordMapper;

    @Override
    public KeywordResponse getKeywordById(int id) {
        Optional<Keyword> keywordEntity = keywordRepository.findById(id);

        if (keywordEntity.isEmpty()) {
            throw new KeywordNotFoundException("Keyword not found.");
        }

        return keywordMapper.INSTANCE.entityToKeywordResponseDto(keywordEntity.get());
    }

    @Override
    public List<KeywordResponse> getKeywords() {
        List<Keyword> keywords = keywordRepository.findAll();

        return keywords.stream()
                .map(keywordMapper.INSTANCE::entityToKeywordResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public List<KeywordResponse> addListOfKeywords(List<KeywordAddingRequest> requestList) {
        try {
            List<Keyword> keywordEntityList =
                    requestList.stream()
                            .map(KeywordMapper.INSTANCE::keywordAddingRequestToEntity)
                            .toList();

            List<Keyword> savedKeyword = keywordRepository.saveAll(keywordEntityList);

            return savedKeyword.stream()
                    .map(keywordMapper::entityToKeywordResponseDto)
                    .toList();

        } catch (Exception e) {
            throw new GeneralDatabaseException("Error saving keyword", e);
        }
    }

    @Override
    public KeywordResponse addKeyword(KeywordAddingRequest keywordAddingRequest) {
        try {
            Keyword keywordEntity = KeywordMapper.INSTANCE.keywordAddingRequestToEntity(keywordAddingRequest);

            Keyword savedKeyword = keywordRepository.save(keywordEntity);

            return keywordMapper.INSTANCE.entityToKeywordResponseDto(savedKeyword);

        } catch (Exception e) {
            throw new GeneralDatabaseException("Error saving keyword", e);
        }

    }
}
