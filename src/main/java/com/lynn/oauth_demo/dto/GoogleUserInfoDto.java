package com.lynn.oauth_demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Lynn on 2025/4/27
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class GoogleUserInfoDto {

  /** 用戶唯一識別碼 */
  @JsonProperty("sub")
  private String sub;

  @JsonProperty("name")
  private String name;

  @JsonProperty("given_name")
  private String givenName;

  @JsonProperty("family_name")
  private String familyName;

  @JsonProperty("picture")
  private String picture;

  @JsonProperty("email")
  private String email;

  @JsonProperty("email_verified")
  private Boolean emailVerified;

  @JsonProperty("issued_at")
  private long issuedAt;

  @JsonProperty("expires_at")
  private long expiresAt;

}
