package com.lynn.oauth_demo.valid;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.Instant;
import java.util.Date;

/**
 * @Author: Lynn on 2025/5/6
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class JwtValidator {

  private static final String JWKS_URI =
      "https://www.googleapis.com/oauth2/v3/certs";
  private static final String ISSUER = "https://accounts.google.com";

  @Value("${google.oauth.clientId}")
  private String clientId;

  private final ConfigurableJWTProcessor<SecurityContext> jwtProcessor;

  public JwtValidator() throws Exception {
    jwtProcessor = new DefaultJWTProcessor<>();

    JWKSource<SecurityContext> keySource = new RemoteJWKSet<>(
        new URL(JWKS_URI)
    );

    JWSAlgorithm expectedAlg = JWSAlgorithm.RS256;
    JWSKeySelector<SecurityContext> keySelector =
        new JWSVerificationKeySelector<>(expectedAlg, keySource);

    jwtProcessor.setJWSKeySelector(keySelector);
  }

  public JWTClaimsSet validate(String idToken) throws Exception {
    SignedJWT signedJWT = SignedJWT.parse(idToken);

    // 驗證簽章並提取 Claims
    SecurityContext ctx = null;
    JWTClaimsSet claims = jwtProcessor.process(signedJWT, ctx);

    // 驗證標準 Claim
    // issuer
    if (!ISSUER.equals(claims.getIssuer())) {
      throw new IllegalStateException("Invalid issuer: " + claims.getIssuer());
    }
    // audience 包含你的 client id
    if (!claims.getAudience().contains(clientId)) {
      throw new IllegalStateException("Invalid audience: " + claims.getAudience());
    }
    // exp、iat
    Date now = Date.from(Instant.now());
    if (claims.getExpirationTime() == null || now.after(claims.getExpirationTime())) {
      throw new IllegalStateException("ID token expired at: " + claims.getExpirationTime());
    }
    if (claims.getNotBeforeTime() != null && now.before(claims.getNotBeforeTime())) {
      throw new IllegalStateException("ID token not yet valid: " + claims.getNotBeforeTime());
    }

    return claims;
  }
}
