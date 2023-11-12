package com.example.coffee.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateGitInfoRequest {
    private String owner;
    private String repository;
    private String description;
}
