package com.example.coffee.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RepositoryResponseDto {
    private String owner;
    private String repository;
    private String version;
    private String description;
}
