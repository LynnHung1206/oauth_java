package com.lynn.oauth_demo;

import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RetrofitScan(basePackages = "com.lynn.oauth_demo.client")
public class OauthDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(OauthDemoApplication.class, args);
  }

}
