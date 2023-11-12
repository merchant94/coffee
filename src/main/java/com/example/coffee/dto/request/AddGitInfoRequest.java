package com.example.coffee.dto.request;

import com.example.coffee.domain.GitInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddGitInfoRequest {
    private String owner;
    private String repository;
    private String version;
    private String description;

    public GitInfo toEntity(){  // 생성자를 사용해 객체 생성
        return GitInfo.builder()
                .owner(owner)
                .repository(repository)
                .version(version)
                .repository(description)
                .build();
    }

}
