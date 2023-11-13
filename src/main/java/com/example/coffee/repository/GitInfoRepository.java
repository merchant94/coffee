package com.example.coffee.repository;


import com.example.coffee.domain.GitInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GitInfoRepository extends JpaRepository<GitInfo, Long> {
    GitInfo findByOwnerAndRepository(String owner, String repository);
    boolean existsByOwnerAndRepository(String owner, String repository);
}
