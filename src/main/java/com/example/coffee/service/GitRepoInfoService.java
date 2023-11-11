package com.example.coffee.service;

import com.example.coffee.exception.CustomException;
import com.example.coffee.exception.NotFoundException;
import com.example.coffee.model.GitHubReleaseInfo;
import com.example.coffee.model.GitRepoInfo;
import com.example.coffee.repository.GitRepoInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class GitRepoInfoService {
    @Autowired
    private final GitRepoInfoRepository gitRepoInfoRepository;
    private final GitHubService gitHubService;
    private static final Logger logger = LoggerFactory.getLogger(GitRepoInfoService.class);

    public GitRepoInfoService(GitRepoInfoRepository gitRepoInfoRepository, GitHubService gitHubService) {
        this.gitRepoInfoRepository = gitRepoInfoRepository;
        this.gitHubService = gitHubService;
    }


    public List<GitRepoInfo> getAllGitRepositories() {
        return gitRepoInfoRepository.findAll();
    }

    public void saveGitRepositoryInfo(GitRepoInfo gitRepoInfo) {
        String owner = gitRepoInfo.getOwner();
        String repository = gitRepoInfo.getRepository();

        // DB에 동일한 (owner, repository)가 존재하는지 확인
        GitRepoInfo existingRepo = gitRepoInfoRepository.findByOwnerAndRepository(owner, repository);
        if (existingRepo != null) {
            throw new CustomException("Repository already exists: " + existingRepo);
        }

        // Get release version from GitHub API
        Mono<GitHubReleaseInfo> releaseInfoMono = gitHubService.getTagName(owner, repository);

        // 동기화 메서드 호출 사용하여 예외 처리
        GitHubReleaseInfo releaseInfo = releaseInfoMono.block();
        if (releaseInfo == null) {
            throw new NotFoundException("Repository not found on GitHub");
        }

        // Set the version, description and save to the DB
        gitRepoInfo.setVersion(releaseInfo.getTagName());
        gitRepoInfoRepository.save(gitRepoInfo);
    }

    public void deleteRepository(GitRepoInfo gitRepoInfo) {
        
    }

    public void update(GitRepoInfo gitRepoInfo) {
        String owner = gitRepoInfo.getOwner();
        String repository = gitRepoInfo.getRepository();

        // DB에 동일한 (owner, repository)가 존재하는지 확인
        GitRepoInfo existingRepo = gitRepoInfoRepository.findByOwnerAndRepository(owner, repository);
        if (existingRepo != null) {
            existingRepo.setDescription(gitRepoInfo.getDescription());
            gitRepoInfoRepository.save(existingRepo);
        } else {
            throw new NotFoundException("Repository does not exist");
        }
    }
}
