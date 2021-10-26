package com.app.IssueAnalysis.mapper;

import static com.app.IssueAnalysis.model.VoteType.DOWNVOTE;
import static com.app.IssueAnalysis.model.VoteType.UPVOTE;

import java.util.Optional;

import com.app.IssueAnalysis.dto.PostRequest;
import com.app.IssueAnalysis.dto.PostResponse;
import com.app.IssueAnalysis.model.IssueAnalysis;
import com.app.IssueAnalysis.model.Post;
import com.app.IssueAnalysis.model.User;
import com.app.IssueAnalysis.model.Vote;
import com.app.IssueAnalysis.model.VoteType;
import com.app.IssueAnalysis.repository.CommentRepository;
import com.app.IssueAnalysis.repository.VoteRepository;
import com.app.IssueAnalysis.service.AuthService;
import com.github.marlonlom.utilities.timeago.TimeAgo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthService authService;

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subissues", source = "subissues")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source = "user")
    public abstract Post map(PostRequest postRequest, IssueAnalysis subissues, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subissuesName", source = "subissues.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    @Mapping(target = "upVote", expression = "java(isPostUpVoted(post))")
    @Mapping(target = "downVote", expression = "java(isPostDownVoted(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post) {
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }

    boolean isPostUpVoted(Post post) {
        return checkVoteType(post, UPVOTE);
    }

    boolean isPostDownVoted(Post post) {
        return checkVoteType(post, DOWNVOTE);
    }

    private boolean checkVoteType(Post post, VoteType voteType) {
        if (authService.isLoggedIn()) {
            Optional<Vote> voteForPostByUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post,
                    authService.getCurrentUser());
            return voteForPostByUser.filter(vote -> vote.getVoteType().equals(voteType)).isPresent();
        }
        return false;
    }

}