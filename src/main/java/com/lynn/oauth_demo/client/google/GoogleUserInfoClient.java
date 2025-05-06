package com.lynn.oauth_demo.client.google;

import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitClient;
import com.lynn.oauth_demo.dto.GoogleUserInfoDto;
import org.springframework.stereotype.Component;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * @Author: Lynn on 2025/4/27
 * 實作 oauth2.0 的 Google 用戶資訊獲取 oidc用不到
 */
@RetrofitClient(baseUrl = "https://www.googleapis.com")
@Component
public interface GoogleUserInfoClient {

  @GET("/oauth2/v3/userinfo")
  GoogleUserInfoDto getUserInfo(@Header("Authorization") String authorization);
}

