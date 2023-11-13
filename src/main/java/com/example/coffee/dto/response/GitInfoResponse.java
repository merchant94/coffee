package com.example.coffee.dto.response;

import com.example.coffee.domain.GitInfo;
import lombok.Getter;

@Getter
public class GitInfoResponse {
    private final String owner;
    private final String repository;
    private final String version;
    private final String description;

    public GitInfoResponse(GitInfo gitInfo) {
        this.owner = gitInfo.getOwner();
        this.repository = gitInfo.getRepository();
        this.version = gitInfo.getVersion();
        this.description = gitInfo.getDescription();
    }

}
