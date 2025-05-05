package com.lynn.oauth_demo.controller.googleOauth;

import com.lynn.oauth_demo.client.google.GoogleOauthClient;
import com.lynn.oauth_demo.client.google.GoogleUserInfoClient;
import com.lynn.oauth_demo.dto.GoogleUserInfoDto;
import com.lynn.oauth_demo.service.OauthProviderService;
import com.lynn.oauth_demo.vo.OauthProvidersVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Lynn on 2025/4/27
 */
@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class GoogleOauth2Controller {

  private final GoogleOauthClient googleOauthClient;

  private final GoogleUserInfoClient googleUserInfoClient;

  private final OauthProviderService oauthProviderService;


  @Value("${google.oauth.clientId}")
  private String clientId;

  @Value("${google.oauth.clientSecret}")
  private String clientSecret;

  @GetMapping("/oauth2/callback")
  public Object oauthCallback(@RequestParam("code") String code) {

    OauthProvidersVo provider = oauthProviderService.getOauthProviderById(1);
    Map<String, String> fields = new HashMap<>();
    fields.put("code", code);
    fields.put("client_id", provider.getClientId());
    fields.put("client_secret", provider.getClientSecret());
    fields.put("redirect_uri", "http://localhost:8080/oauth2/callback");
    fields.put("grant_type", "authorization_code");

    Map<String, Object> body = googleOauthClient.getAccessToken(fields);
    String token = (String) body.get("access_token");

    String authorizationHeader = "Bearer " + token;
    GoogleUserInfoDto userInfo = googleUserInfoClient.getUserInfo(authorizationHeader);

    return ResponseEntity.ok(userInfo);
  }


}
