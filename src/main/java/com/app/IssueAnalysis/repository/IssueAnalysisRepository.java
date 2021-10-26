package com.app.IssueAnalysis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.IssueAnalysis.model.IssueAnalysis;

public interface IssueAnalysisRepository extends JpaRepository<IssueAnalysis, Long> {
    Optional<IssueAnalysis> findByName(String subissuesName);
}
