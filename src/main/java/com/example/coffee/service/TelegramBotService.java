package com.example.coffee.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class TelegramBotService {
    @Value("${telegram.bot.token}")
    private String botToken;
    @Value("${telegram.chat.id}")
    private String chatId;
    @Value("${telegram.api.url}")
    private String telegramApiUrl;

    public void sendTelegramMessage(String message){
        // Send Telegram Alarm message
        String apiUrl = String.format(telegramApiUrl, botToken, chatId, message);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(apiUrl, String.class);

        log.info("Send Telegram Alarm message: {}", message);
        log.info("Telegram API response: {}", response);
    }
}
