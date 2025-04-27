package com.lynn.oauth_demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.net.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/api")
@Slf4j
public class LoginController {

  @Value("${google.oauth.clientId}")
  private String clientId;

  @Value("${google.oauth.redirectUri}")
  private String redirectUri;


  @GetMapping("/login/google")
  public ResponseEntity<Void> loginWithGoogle() throws URISyntaxException {

    URI uri = new URIBuilder("https://accounts.google.com/o/oauth2/v2/auth")
        .addParameter("client_id", clientId)
        .addParameter("redirect_uri", redirectUri)
        .addParameter("response_type", "code")
        .addParameter("scope", "openid email profile")
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
