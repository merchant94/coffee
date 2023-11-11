package com.example.coffee.controller;

import com.example.coffee.dto.response.RepositoryResponseDto;
import com.example.coffee.dto.response.common.CommonResponse;
import com.example.coffee.dto.response.repository.RepositoryInfoDto;
import com.example.coffee.model.GitRepoInfo;
import com.example.coffee.service.GitRepoInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/repository")
@Tag(name = "repository API")
public class GitRepoInfoControllerV2 {
    private final GitRepoInfoService gitRepoInfoService;

    @Operation(summary = "Add a repository", description = "Add a new repository.")
    @PostMapping("")
    public CommonResponse<RepositoryInfoDto> addRepository(
            @RequestParam(value = "owner", required = true) String owner,
            @RequestParam(value = "repository", required = true) String repository,
            @RequestParam(value = "description", required = false) String description){

        RepositoryInfoDto repositoryInfoDto = gitRepoInfoService.saveGitRepositoryInfo(owner, repository, description);

        return new CommonResponse<>(repositoryInfoDto);
    }

    @Operation(summary = "Get all repositories", description = "Get a list of all repositories.")
    @GetMapping("")
    public CommonResponse<List<GitRepoInfo>> getAllRepositories() {

        List<GitRepoInfo> repositoryInfoDtos = gitRepoInfoService.getAllGitRepositories();

        return new CommonResponse<>(repositoryInfoDtos);
    }

    @Operation(summary = "Update description")
    @PutMapping("")
    public CommonResponse<RepositoryInfoDto> updateRepository(
            @RequestParam(value = "owner", required = true) String owner,
            @RequestParam(value = "repository", required = true) String repository,
            @RequestParam(value = "description", required = true) String description){

        RepositoryInfoDto repositoryInfoDto = gitRepoInfoService.updateGitRepositoryInfo(owner, repository, description);

        return new CommonResponse<>(repositoryInfoDto);
    }

    @Operation(summary = "Delete repository")
    @DeleteMapping("")
    public CommonResponse<RepositoryInfoDto> deleteRepository(
            @RequestParam(value = "owner", required = true) String owner,
            @RequestParam(value = "repository", required = true) String repository){

        boolean deleted = gitRepoInfoService.deleteGitRepositoryInfo(owner, repository);

        return new CommonResponse(deleted);

    }

}
