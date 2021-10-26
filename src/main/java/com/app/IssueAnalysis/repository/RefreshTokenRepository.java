package com.app.IssueAnalysis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.IssueAnalysis.model.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long>{
    Optional<RefreshToken> findByToken(String Token) ;

    void deleteByToken(String Token) ;
}
