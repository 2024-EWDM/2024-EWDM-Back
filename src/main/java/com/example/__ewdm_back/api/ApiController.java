package com.example.__ewdm_back.api;

import io.github.redouane59.twitter.TwitterClient;
import io.github.redouane59.twitter.dto.tweet.Tweet;
import io.github.redouane59.twitter.dto.user.User;
import io.github.redouane59.twitter.signature.TwitterCredentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ApiController {

//    @Value("${twitter.apiKey}")
//    private String apiKey;
//
//    @Value("${twitter.apiSecretKey}")
//    private String apiSecretKey;
//
//    @Value("${twitter.accessToken}")
//    private String accessToken;
//
//    @Value("${twitter.accessTokenSecret}")
//    private String accessTokenSecret;
//    @GetMapping("/news/latest")
//    public ResponseEntity<?> getLatestNews() {
//        TwitterClient twitterClient = new TwitterClient(TwitterCredentials.builder()
//                .accessToken(accessToken)
//                .accessTokenSecret(accessTokenSecret)
//                .apiKey(apiKey)
//                .apiSecretKey(apiSecretKey)
//                .build());
//
//    }
}
