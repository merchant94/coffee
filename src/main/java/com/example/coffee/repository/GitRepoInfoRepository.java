package com.example.coffee.repository;

import com.example.coffee.model.GitRepoInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GitRepoInfoRepository extends JpaRepository<GitRepoInfo, Long> {
    GitRepoInfo findByOwnerAndRepository(String owner, String repository);
}
