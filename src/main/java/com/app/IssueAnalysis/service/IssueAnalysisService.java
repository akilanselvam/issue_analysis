package com.app.IssueAnalysis.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.transaction.Transactional;

import com.app.IssueAnalysis.dto.IssueAnalysisDto;
import com.app.IssueAnalysis.exceptions.IssueAnalysisException;
import com.app.IssueAnalysis.mapper.IssueAnalysisMapper;
import com.app.IssueAnalysis.model.IssueAnalysis;
import com.app.IssueAnalysis.repository.IssueAnalysisRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class IssueAnalysisService {

    private final IssueAnalysisRepository subissuesRepository;
    private final IssueAnalysisMapper subissuesMapper;

    @Transactional
    public IssueAnalysisDto save(IssueAnalysisDto subissuesDto) {
        IssueAnalysis save = subissuesRepository.save(subissuesMapper.mapDtoToSubissues(subissuesDto));
        subissuesDto.setId(save.getId());
        return subissuesDto;
    }

    public List<IssueAnalysisDto> getAll() {
        return subissuesRepository.findAll().stream().map(subissuesMapper::mapSubissuesToDto)
                .collect(toList());
    }

    public IssueAnalysisDto getSubissues(Long id) {
        IssueAnalysis subissues = subissuesRepository.findById(id)
                .orElseThrow(() -> new IssueAnalysisException("No subissues found with ID - " + id));
        return subissuesMapper.mapSubissuesToDto(subissues);
    }
}
