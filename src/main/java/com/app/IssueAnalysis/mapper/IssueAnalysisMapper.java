package com.app.IssueAnalysis.mapper;

import java.util.List;

import com.app.IssueAnalysis.dto.IssueAnalysisDto;
import com.app.IssueAnalysis.model.IssueAnalysis;
import com.app.IssueAnalysis.model.Post;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IssueAnalysisMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subissues.getPosts()))")
    IssueAnalysisDto mapSubissuesToDto(IssueAnalysis subissues);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    IssueAnalysis mapDtoToSubissues(IssueAnalysisDto subissuesDto);
}
