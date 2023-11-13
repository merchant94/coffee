package com.example.coffee.service;

import com.example.coffee.domain.GitHubReleaseInfo;
import com.example.coffee.domain.GitInfo;
import com.example.coffee.exception.NotFoundException;
import com.example.coffee.repository.GitInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GitInfoScheduler {

    @Autowired
    private GitInfoService gitInfoService;
    @Autowired
    private TelegramBotService telegramBotService;

    private final GitHubAPIService gitHubAPIService;

    private final GitInfoRepository gitInfoRepository;
    private static final Logger logger = LoggerFactory.getLogger(GitInfoScheduler.class);

    public GitInfoScheduler(GitHubAPIService gitHubAPIService, GitInfoRepository gitInfoRepository) {
        this.gitHubAPIService = gitHubAPIService;
        this.gitInfoRepository = gitInfoRepository;
    }

    @Scheduled(cron = "0 0 * * * *") // 매 시간 0분에 실행
    public void fetchAndUpdateGitInfo() {

        logger.info("Scheduled task(fetchAndUpdateGitInfo) executed at {}", LocalDateTime.now());

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
