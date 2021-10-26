package com.app.IssueAnalysis.mapper;

import com.app.IssueAnalysis.dto.IssueAnalysisDto;
import com.app.IssueAnalysis.model.IssueAnalysis;
import com.app.IssueAnalysis.model.IssueAnalysis.IssueAnalysisBuilder;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-26T14:53:30+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class IssueAnalysisMapperImpl implements IssueAnalysisMapper {

    @Override
    public IssueAnalysisDto mapSubissuesToDto(IssueAnalysis subissues) {
        if ( subissues == null ) {
            return null;
        }

        IssueAnalysisDto issueAnalysisDto = new IssueAnalysisDto();

        issueAnalysisDto.setId( subissues.getId() );
        issueAnalysisDto.setName( subissues.getName() );
        issueAnalysisDto.setDescription( subissues.getDescription() );

        issueAnalysisDto.setNumberOfPosts( mapPosts(subissues.getPosts()) );

        return issueAnalysisDto;
    }

    @Override
    public IssueAnalysis mapDtoToSubissues(IssueAnalysisDto subissuesDto) {
        if ( subissuesDto == null ) {
            return null;
        }

        IssueAnalysisBuilder issueAnalysis = IssueAnalysis.builder();

        issueAnalysis.id( subissuesDto.getId() );
        issueAnalysis.name( subissuesDto.getName() );
        issueAnalysis.description( subissuesDto.getDescription() );

        return issueAnalysis.build();
    }
}
