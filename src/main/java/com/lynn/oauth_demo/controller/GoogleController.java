package com.lynn.oauth_demo.controller;

import com.lynn.oauth_demo.client.GoogleOauthClient;
import com.lynn.oauth_demo.client.GoogleUserInfoClient;
import com.lynn.oauth_demo.dto.UserInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Lynn on 2025/4/27
 */
@RestController
@Slf4j
public class GoogleController {

  private final GoogleOauthClient googleOauthClient;

  private final GoogleUserInfoClient googleUserInfoClient;

  GoogleController(GoogleOauthClient googleOauthClient,
                   GoogleUserInfoClient googleUserInfoClient) {
    this.googleOauthClient = googleOauthClient;
    this.googleUserInfoClient = googleUserInfoClient;
  }

  @Value("${google.oauth.clientId}")
  private String clientId;

  @Value("${google.oauth.clientSecret}")
  private String clientSecret;

  @GetMapping("/oauth2/callback")
  public Object oauthCallback(@RequestParam("code") String code) {

    Map<String, String> fields = new HashMap<>();
    fields.put("code", code);
    fields.put("client_id", clientId);
    fields.put("client_secret", clientSecret);
    fields.put("redirect_uri", "http://localhost:8080/oauth2/callback");
    fields.put("grant_type", "authorization_code");

    Map<String, Object> body = googleOauthClient.getAccessToken(fields);
    String token = (String) body.get("access_token");

    String authorizationHeader = "Bearer " + token;
    UserInfoDto userInfo = googleUserInfoClient.getUserInfo(authorizationHeader);

    return ResponseEntity.ok(userInfo);
  }


}
