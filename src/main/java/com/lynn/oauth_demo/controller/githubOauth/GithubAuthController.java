package com.lynn.oauth_demo.controller.githubOauth;

import com.lynn.oauth_demo.client.github.GithubOauthClient;
import com.lynn.oauth_demo.client.github.GithubUserInfoClient;
import com.lynn.oauth_demo.dto.GithubTokenResponse;
import com.lynn.oauth_demo.dto.GithubUserInfoDto;
import com.lynn.oauth_demo.service.OauthProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Lynn on 2025/5/5
 */
@RestController
@RequestMapping("/api/oauth2/callback")
@RequiredArgsConstructor
public class GithubAuthController {

  private final GithubOauthClient githubOauthClient;

  private final GithubUserInfoClient githubUserInfoClient;

  private final OauthProviderService oauthProviderService;
  @GetMapping("/github")
  public ResponseEntity<?> githubCallback(@RequestParam("code") String code) {
    var provider = oauthProviderService.getOauthProviderById(2);

    Map<String, String> tokenRequest = new HashMap<>();
    tokenRequest.put("client_id", provider.getClientId());
    tokenRequest.put("client_secret", provider.getClientSecret());
    tokenRequest.put("code", code);
    tokenRequest.put("redirect_uri", provider.getRedirectUri());

    GithubTokenResponse tokenResponse = githubOauthClient.getAccessToken(tokenRequest);

    GithubUserInfoDto userInfo = githubUserInfoClient.getUserInfo(
        "Bearer " + tokenResponse.getAccessToken());

    return ResponseEntity.ok(userInfo);
  }
}
