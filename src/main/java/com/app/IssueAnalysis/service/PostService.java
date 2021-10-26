package com.app.IssueAnalysis.service;

import java.util.List;

import static java.util.stream.Collectors.toList;

import com.app.IssueAnalysis.dto.PostRequest;
import com.app.IssueAnalysis.dto.PostResponse;
import com.app.IssueAnalysis.exceptions.PostNotFoundException;
import com.app.IssueAnalysis.exceptions.SubissuesNotFoundException;
import com.app.IssueAnalysis.mapper.PostMapper;
import com.app.IssueAnalysis.model.IssueAnalysis;
import com.app.IssueAnalysis.model.Post;
import com.app.IssueAnalysis.model.User;
import com.app.IssueAnalysis.repository.IssueAnalysisRepository;
import com.app.IssueAnalysis.repository.PostRepository;
import com.app.IssueAnalysis.repository.UserRepository;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final IssueAnalysisRepository subissuesRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public void save(PostRequest postRequest) {
        IssueAnalysis subissues = subissuesRepository.findByName(postRequest.getSubissuesName())
                .orElseThrow(() -> new SubissuesNotFoundException(postRequest.getSubissuesName()));
        Post post = postMapper.map(postRequest, subissues, authService.getCurrentUser());
        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubissues(Long subissuesId) {
        IssueAnalysis subissues = subissuesRepository.findById(subissuesId)
                .orElseThrow(() -> new SubissuesNotFoundException(subissuesId.toString()));
        List<Post> posts = postRepository.findAllBySubissues(subissues);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user).stream().map(postMapper::mapToDto).collect(toList());
    }
}
