package com.example.coffee.service;

import com.example.coffee.exception.CustomException;
import com.example.coffee.exception.NotFoundException;
import com.example.coffee.model.GitHubReleaseInfo;
import com.example.coffee.model.GitRepoInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class GitHubService {
    private final WebClient webClient;

    public GitHubService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.github.com").build();
    }

    public Mono<GitHubReleaseInfo> getTagName(String owner, String repo){
        return webClient
                .get()
                .uri("/repos/{owner}/{repo}/releases/latest",owner,repo)
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
