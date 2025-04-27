package com.lynn.oauth_demo.client;

import com.github.lianjiatech.retrofit.spring.boot.core.ErrorDecoder;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author: Lynn on 2025/4/27
 */
public class GoogleErrorDecoder implements ErrorDecoder {
  @Override
  public RuntimeException invalidRespDecode(Request request, Response response) {
    return null;
  }
}
