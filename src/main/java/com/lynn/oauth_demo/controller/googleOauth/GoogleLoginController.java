package com.lynn.oauth_demo.controller.googleOauth;

import com.lynn.oauth_demo.service.OauthProviderService;
import com.lynn.oauth_demo.vo.OauthProvidersVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.net.URIBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;


/**
 * @Author: Lynn on 2025/4/27
 */
@RestController
@RequestMapping("/api/oauth")
@Slf4j
@RequiredArgsConstructor
public class GoogleLoginController {


  private final OauthProviderService oauthProviderService;

  @GetMapping("/login/google")
  public ResponseEntity<Void> loginWithGoogle() throws URISyntaxException {

    OauthProvidersVo provider = oauthProviderService.getOauthProviderById(1);

    URI uri = new URIBuilder(provider.getAuthorizationEndpoint())
        .addParameter("client_id", provider.getClientId())
        .addParameter("redirect_uri", provider.getRedirectUri())
        .addParameter("response_type", "code")
        .addParameter("scope", provider.getScope())
        .addParameter("access_type", "offline")
        .addParameter("prompt", "consent")
        .addParameter("include_granted_scopes", "false")
        .build();

    String redirectUrl = uri.toString();

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(URI.create(redirectUrl));
    return new ResponseEntity<>(headers, HttpStatus.FOUND);
  }

}
