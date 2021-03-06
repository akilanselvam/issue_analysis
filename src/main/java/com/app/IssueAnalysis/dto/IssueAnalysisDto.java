package com.app.IssueAnalysis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueAnalysisDto {
    private Long id;
    private String name;
    private String description;
    private Integer numberOfPosts;
}
