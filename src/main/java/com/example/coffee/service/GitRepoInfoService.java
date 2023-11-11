package com.example.coffee.service;

import com.example.coffee.dto.response.repository.RepositoryInfoDto;
import com.example.coffee.exception.CustomException;
import com.example.coffee.exception.NotFoundException;
import com.example.coffee.model.GitHubReleaseInfo;
import com.example.coffee.model.GitRepoInfo;
import com.example.coffee.repository.GitRepoInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

        // TODO
        // RepositoryInfoDto로 반환할 수 있도록 수정??

        return gitRepoInfoRepository.findAll();
    }

    public RepositoryInfoDto saveGitRepositoryInfo(String owner, String repository, String description) {

        // DB 동일 repo 확인
        if (gitRepoInfoRepository.existsByOwnerAndRepository(owner, repository)) {
            throw new CustomException("Repository already exists for owner: " + owner + ", repository: " + repository);
        }

        // Get release version from GitHub API
        Mono<GitHubReleaseInfo> releaseInfoMono = gitHubService.getTagName(owner, repository);
        GitHubReleaseInfo releaseInfo = releaseInfoMono.blockOptional()
                .orElseThrow(() -> new NotFoundException("Repository not found on GitHub"));

        GitRepoInfo savedRepo = gitRepoInfoRepository.save(
                new GitRepoInfo(owner, repository, releaseInfo.getTagName(), description));


        return toRepositoryInfoDto(savedRepo);
    }

    public boolean deleteGitRepositoryInfo(String owner, String repository) {
        try {
            // DB 동일 repo 확인
            GitRepoInfo existingRepo = gitRepoInfoRepository.findByOwnerAndRepository(owner, repository);

            if (existingRepo == null) {
                throw new NotFoundException("Repository does not exist");
            } else {
                gitRepoInfoRepository.delete(existingRepo);
                return true; // 삭제 성공 시 true 반환
            }

        } catch (EmptyResultDataAccessException ex) {
            return false; // 예외 발생 시 false 반환
        }
    }

    public RepositoryInfoDto updateGitRepositoryInfo(String owner, String repository, String description) {

        GitRepoInfo existingRepo = gitRepoInfoRepository.findByOwnerAndRepository(owner, repository);

        if (existingRepo == null) {
            throw new NotFoundException("Repository does not exist");
        } else {
            existingRepo.setDescription(description);
            gitRepoInfoRepository.save(existingRepo);
            return toRepositoryInfoDto(existingRepo);
        }
    }

    private RepositoryInfoDto toRepositoryInfoDto(GitRepoInfo gitRepoInfo) {
        return RepositoryInfoDto.builder()
                .owner(gitRepoInfo.getOwner())
                .repository(gitRepoInfo.getRepository())
                .version(gitRepoInfo.getVersion())
                .description(gitRepoInfo.getDescription())
                .build();
    }
}
