package com.lynn.oauth_demo.controller.googleOauth;

import com.lynn.oauth_demo.client.google.GoogleOauthClient;
import com.lynn.oauth_demo.client.google.GoogleUserInfoClient;
import com.lynn.oauth_demo.dto.GoogleTokenResponseDto;
import com.lynn.oauth_demo.dto.GoogleUserInfoDto;
import com.lynn.oauth_demo.service.OauthProviderService;
import com.lynn.oauth_demo.valid.JwtValidator;
import com.lynn.oauth_demo.vo.OauthProvidersVo;
import com.nimbusds.jwt.JWTClaimsSet;
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

  private final OauthProviderService oauthProviderService;

  private final JwtValidator validator;
  @GetMapping("/oauth2/callback")
  public Object oauthCallback(@RequestParam("code") String code) throws Exception {

    OauthProvidersVo provider = oauthProviderService.getOauthProviderById(1);
    Map<String, String> fields = new HashMap<>();
    fields.put("code", code);
    fields.put("client_id", provider.getClientId());
    fields.put("client_secret", provider.getClientSecret());
    fields.put("redirect_uri", provider.getRedirectUri());
    fields.put("grant_type", "authorization_code");

    GoogleTokenResponseDto accessToken = googleOauthClient.getAccessToken(fields);

    String idToken = accessToken.getIdToken();
    JWTClaimsSet claims = validator.validate(idToken);

    GoogleUserInfoDto userInfoDto = GoogleUserInfoDto.builder()
        .sub(claims.getSubject())
        .name(claims.getClaim("name").toString())
        .givenName(claims.getClaim("given_name").toString())
        .familyName(claims.getClaim("family_name").toString())
        .picture(claims.getClaim("picture").toString())
        .email(claims.getClaim("email").toString())
        .emailVerified(Boolean.valueOf(claims.getClaim("email_verified").toString()))
        .issuedAt(claims.getIssueTime().getTime())
        .expiresAt(claims.getExpirationTime().getTime())
        .build();

    Map<String, Object> response = new HashMap<>();
    response.put("userInfo", userInfoDto);
    response.put("tokens", accessToken);

    return ResponseEntity.ok(response);
  }


}
