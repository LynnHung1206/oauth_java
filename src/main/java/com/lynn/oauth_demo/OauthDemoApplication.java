package com.lynn.oauth_demo;

import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@RetrofitScan(basePackages = "com.lynn.oauth_demo.client")
@PropertySource("classpath:application-secret.properties")
public class OauthDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(OauthDemoApplication.class, args);
  }

}
