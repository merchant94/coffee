package com.example.coffee.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown properties
public class GitHubReleaseInfo {

    @JsonProperty("tag_name")
    private String tagName;

}
