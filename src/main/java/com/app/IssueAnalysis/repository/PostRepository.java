package com.app.IssueAnalysis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.IssueAnalysis.model.IssueAnalysis;
import com.app.IssueAnalysis.model.Post;
import com.app.IssueAnalysis.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubissues(IssueAnalysis subissues);

    List<Post> findByUser(User user);
}
