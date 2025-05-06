package com.lynn.oauth_demo.client.google;

import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitClient;
import com.lynn.oauth_demo.dto.GoogleTokenResponseDto;
import org.springframework.stereotype.Component;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.Map;

/**
 * @Author: Lynn on 2025/4/27
 */
@RetrofitClient(baseUrl = "https://oauth2.googleapis.com")
@Component
public interface GoogleOauthClient {

  @POST("/token")
  @FormUrlEncoded
  GoogleTokenResponseDto getAccessToken(@FieldMap Map<String, String> fields);
}

