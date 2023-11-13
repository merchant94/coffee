package com.example.coffee.service;

import com.example.coffee.domain.GitHubReleaseInfo;
import com.example.coffee.domain.GitInfo;
import com.example.coffee.exception.NotFoundException;
import com.example.coffee.repository.GitInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@EnableScheduling
public class GitInfoScheduler {


    private final GitInfoService gitInfoService;
    private final TelegramBotService telegramBotService;
    private final GitHubAPIService gitHubAPIService;
    private final GitInfoRepository gitInfoRepository;

    public GitInfoScheduler(GitInfoService gitInfoService, TelegramBotService telegramBotService, GitHubAPIService gitHubAPIService, GitInfoRepository gitInfoRepository) {
        this.gitInfoService = gitInfoService;
        this.telegramBotService = telegramBotService;
        this.gitHubAPIService = gitHubAPIService;
        this.gitInfoRepository = gitInfoRepository;
    }


    @Scheduled(initialDelay = 60000, fixedRate = 1800000) // initialDelay: 1min, fixedRate: 30min
    public void fetchAndUpdateGitInfo() {

        log.info("Scheduled task(fetchAndUpdateGitInfo) executed at {}", LocalDateTime.now());

        List<GitInfo> gitInfos = gitInfoService.findAll();

        for (GitInfo gitInfo : gitInfos) {
            Mono<GitHubReleaseInfo> releaseInfoMono = gitHubAPIService.getReleaseInfo(gitInfo.getOwner(), gitInfo.getRepository());
            GitHubReleaseInfo releaseInfo = releaseInfoMono.blockOptional()
                    .orElseThrow(() -> new NotFoundException("Repository not found on GitHub"));

            if (!releaseInfo.getTagName().equals(gitInfo.getVersion())) {
                gitInfo.setVersion(releaseInfo.getTagName());
                gitInfoRepository.save(gitInfo);
                telegramBotService.sendTelegramMessage(releaseInfo.getHtmlUrl());
            }

        }

    }


}
