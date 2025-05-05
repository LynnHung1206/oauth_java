package com.lynn.oauth_demo.client.github;

import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitClient;
import com.lynn.oauth_demo.dto.GithubTokenResponse;
import org.springframework.stereotype.Component;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import java.util.Map;

/**
 * @Author: Lynn on 2025/5/5
 */
@RetrofitClient(baseUrl = "https://github.com")
@Component
public interface GithubOauthClient {

  @POST("/login/oauth/access_token")
  @Headers({
      "Accept: application/json"
  })
  GithubTokenResponse getAccessToken(@Body Map<String, String> body);
}
