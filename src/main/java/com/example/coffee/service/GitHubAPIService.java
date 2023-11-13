package com.example.coffee.service;

import com.example.coffee.exception.CustomException;
import com.example.coffee.exception.NotFoundException;
import com.example.coffee.domain.GitHubReleaseInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class GitHubAPIService {
    private final WebClient webClient;
    @Value("${github.api.url}")
    private String githubApiUrl;
    @Value("${github.api.release.endpoint}")
    private String githubApiReleaseEndpoint;

    public GitHubAPIService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(githubApiUrl).build();
    }

    public Mono<GitHubReleaseInfo> getReleaseInfo(String owner, String repo){
        return webClient
                .get()
                .uri(githubApiReleaseEndpoint,owner,repo)
                .retrieve()
                .bodyToMono(GitHubReleaseInfo.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    if (e.getRawStatusCode() == 404){
                        return Mono.error(new NotFoundException("Repository not found"));
                    } else {
                        return Mono.error(new CustomException("Error while calling GitHub API"));
                    }
                });
    }
}
