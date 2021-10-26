package com.app.IssueAnalysis.service;

import java.util.List;

import javax.transaction.Transactional;

import com.app.IssueAnalysis.dto.UserDto;
import com.app.IssueAnalysis.exceptions.IssueAnalysisException;
import com.app.IssueAnalysis.mapper.UserMapper;
import com.app.IssueAnalysis.model.User;
import com.app.IssueAnalysis.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional 
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(userMapper::mapUserToDto).collect(toList());
    }

    public UserDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IssueAnalysisException("User with ID: " + id + " not found."));

        return userMapper.mapUserToDto(user);
    }
}
