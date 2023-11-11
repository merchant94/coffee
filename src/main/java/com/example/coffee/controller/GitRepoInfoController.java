package com.example.coffee.controller;

import com.example.coffee.exception.CustomException;
import com.example.coffee.exception.NotFoundException;
import com.example.coffee.model.GitRepoInfo;
import com.example.coffee.repository.GitRepoInfoRepository;
import com.example.coffee.service.GitRepoInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/repository")
public class GitRepoInfoController {

    private final GitRepoInfoService gitRepoInfoService;
    private final GitRepoInfoRepository gitRepoInfoRepository;

    public GitRepoInfoController(GitRepoInfoService gitRepoInfoService, GitRepoInfoRepository gitRepoInfoRepository) {
        this.gitRepoInfoService = gitRepoInfoService;
        this.gitRepoInfoRepository = gitRepoInfoRepository;
    }

//    @PostMapping("")
//    public ResponseEntity<String> addRepository(@RequestBody GitRepoInfo gitRepoInfo) {
//
//        try {
//            gitRepoInfoService.saveGitRepositoryInfo(gitRepoInfo);
//            return ResponseEntity.ok("Repository info added successfully");
//        } catch (NotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());    // 404 Error
//        } catch (CustomException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }
//
//    @GetMapping("")
//    public ResponseEntity<List<GitRepoInfo>> getAllRepositories() {
//
//        List<GitRepoInfo> repositories = gitRepoInfoService.getAllGitRepositories();
//
//        if (repositories.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(repositories);
//        }
//
//        return ResponseEntity.ok(repositories);
//
//    }

//    @PutMapping ("")
//    public ResponseEntity<GitRepoInfo> updateRepository(@RequestBody GitRepoInfo gitRepoInfo){
//        // DB에 동일한 (owner, repository)가 존재하는지 확인
//        GitRepoInfo existingRepo = gitRepoInfoRepository.findByOwnerAndRepository(gitRepoInfo.getOwner(), gitRepoInfo.getRepository());
//
//        if (existingRepo != null){
//            existingRepo.setDescription(gitRepoInfo.getDescription());
//            gitRepoInfoService.saveGitRepositoryInfo(existingRepo);
//            return ResponseEntity.status(HttpStatus.OK).body(existingRepo);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }


//    @DeleteMapping("")
//    public ResponseEntity<String> deleteRepository(@RequestBody GitRepoInfo gitRepoInfo){
//
//        // DB에 동일한 (owner, repository)가 존재하는지 확인
//        GitRepoInfo existingRepo = gitRepoInfoRepository.findByOwnerAndRepository(gitRepoInfo.getOwner(), gitRepoInfo.getRepository());
//
//        if (existingRepo != null){
//            gitRepoInfoRepository.delete(existingRepo);
//            return ResponseEntity.status(HttpStatus.OK).body("Repository deleted successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Repository not found");
//        }
//
//    }

}
