package com.lynn.oauth_demo.client.github;

import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitClient;
import com.lynn.oauth_demo.dto.GithubUserInfoDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * @Author: Lynn on 2025/5/5
 */
@RetrofitClient(baseUrl = "https://api.github.com")
@Component
public interface GithubUserInfoClient {
  @GET("/user")
  GithubUserInfoDto getUserInfo(@Header("Authorization") String authorization);
}
