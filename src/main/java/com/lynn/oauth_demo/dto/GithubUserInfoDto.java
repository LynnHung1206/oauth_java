package com.lynn.oauth_demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author: Lynn on 2025/5/5
 */
@Data
public class GithubUserInfoDto {

  /** 唯一識別id */
  private String id;

  private String login;

  private String name;

  private String email;

  /** 大頭貼 */
  @JsonProperty("avatar_url")
  private String avatarUrl;
}
