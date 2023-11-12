package com.example.coffee.controller;

import com.example.coffee.domain.GitInfo;
import com.example.coffee.dto.request.AddGitInfoRequest;
import com.example.coffee.dto.request.DeleteGitInfoRequest;
import com.example.coffee.dto.response.GitInfoResponse;
import com.example.coffee.dto.request.UpdateGitInfoRequest;
import com.example.coffee.service.GitInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/gitInfo")
public class GitInfoController {
    private final GitInfoService gitInfoService;

    @PostMapping("")
    public ResponseEntity<GitInfo> addGitInfo(@RequestBody AddGitInfoRequest request){
        GitInfo addedGitInfo = gitInfoService.addGitInfo(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(addedGitInfo);
    }

    @GetMapping("")
    public ResponseEntity<List<GitInfoResponse>> findAll(){
        List<GitInfoResponse> gitInfos = gitInfoService.findAll()
                .stream()
                .map(GitInfoResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(gitInfos);
    }

    @PutMapping("")
    public ResponseEntity<GitInfo> updateGitInfo(@RequestBody UpdateGitInfoRequest request){
        GitInfo updatedGitInfo =  gitInfoService.updateGitInfo(request);
        return ResponseEntity.ok()
                .body(updatedGitInfo);
    }

    @DeleteMapping("")
    public ResponseEntity<Boolean> deleteGitInfo(@RequestBody DeleteGitInfoRequest request){
        gitInfoService.deleteGitInfo(request);
        return ResponseEntity.ok()
                .build();
    }

}
