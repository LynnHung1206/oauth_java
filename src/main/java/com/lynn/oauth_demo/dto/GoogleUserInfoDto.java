package com.lynn.oauth_demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author: Lynn on 2025/4/27
 */
@Data
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

}
