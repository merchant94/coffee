package com.example.coffee.dto.response.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RepositoryInfoDto {
    private String owner;
    private String repository;
    private String version;
    private String description;
}
