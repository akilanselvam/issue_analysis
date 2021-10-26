package com.app.IssueAnalysis.controller;

import java.util.List;

import com.app.IssueAnalysis.dto.IssueAnalysisDto;
import com.app.IssueAnalysis.service.IssueAnalysisService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/subissues")
@AllArgsConstructor
@Slf4j
public class IssueAnalysisController {

    private final IssueAnalysisService subissuesService;

    @PostMapping
    public ResponseEntity<IssueAnalysisDto> createSubissues(@RequestBody IssueAnalysisDto subissuesDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subissuesService.save(subissuesDto));
    }

    @GetMapping
    public ResponseEntity<List<IssueAnalysisDto>> getAllSubissuess() {
        return ResponseEntity.status(HttpStatus.OK).body(subissuesService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssueAnalysisDto> getSubissues(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(subissuesService.getSubissues(id));
    }
}
