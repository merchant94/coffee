package com.example.coffee.service;

import com.example.coffee.domain.GitHubReleaseInfo;
import com.example.coffee.domain.GitInfo;
import com.example.coffee.dto.request.AddGitInfoRequest;
import com.example.coffee.dto.request.DeleteGitInfoRequest;
import com.example.coffee.dto.request.UpdateGitInfoRequest;
import com.example.coffee.exception.CustomException;
import com.example.coffee.exception.NotFoundException;
import com.example.coffee.repository.GitInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class GitInfoService {
    private final GitInfoRepository gitInfoRepository;
    private final GitHubAPIService gitHubAPIService;

    public GitInfo addGitInfo(AddGitInfoRequest addGitInfoRequest){

        String owner = addGitInfoRequest.getOwner();
        String repository = addGitInfoRequest.getRepository();
        String description = addGitInfoRequest.getDescription();

        // DB 동일 repo 확인
        if (gitInfoRepository.existsByOwnerAndRepository(owner, repository)) {
            throw new CustomException("Repository already exists");
        }

        // Get release version from GitHub API
        Mono<GitHubReleaseInfo> releaseInfoMono = gitHubAPIService.getReleaseInfo(owner, repository);
        GitHubReleaseInfo releaseInfo = releaseInfoMono.blockOptional()
                .orElseThrow(() -> new NotFoundException("Repository not found on GitHub"));

        log.info("Add GitHub Info: owner={}, repository={}", owner, repository);

        return gitInfoRepository.save(
                new GitInfo(owner, repository, releaseInfo.getTagName(), description));
    }

    public List<GitInfo> findAll() {
        return gitInfoRepository.findAll();
    }

    public void deleteGitInfo(DeleteGitInfoRequest deleteGitInfoRequest){
        try {
            GitInfo existingRepo = gitInfoRepository.findByOwnerAndRepository(
                    deleteGitInfoRequest.getOwner(),
                    deleteGitInfoRequest.getRepository());

            if (existingRepo == null) {
                throw new NotFoundException("Repository does not exist");
            } else {
                log.info("Delete GitHub Info: owner={}, repository={}", deleteGitInfoRequest.getOwner(), deleteGitInfoRequest.getRepository());
                gitInfoRepository.delete(existingRepo);
            }
        } catch (EmptyResultDataAccessException ex) {
        }
    }

    public GitInfo updateGitInfo(UpdateGitInfoRequest request){

        GitInfo existingRepo = gitInfoRepository.findByOwnerAndRepository(
                request.getOwner(),
                request.getRepository());

        if (existingRepo == null){
            throw new NotFoundException("Repository does not exist");
        } else {
            existingRepo.setDescription(request.getDescription());
            log.info("Update GitHub Info: owner={}, repository={}, description={}",
                    existingRepo.getOwner(),
                    existingRepo.getRepository(),
                    existingRepo.getDescription());
            gitInfoRepository.save(existingRepo);
            return existingRepo;
        }
    }




}
