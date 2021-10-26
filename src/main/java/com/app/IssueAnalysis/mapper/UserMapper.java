package com.app.IssueAnalysis.mapper;

import com.app.IssueAnalysis.dto.UserDto;
import com.app.IssueAnalysis.model.User;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto mapUserToDto(User user);

    @InheritInverseConfiguration
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    User mapDtoToUser(UserDto userDto);
}
