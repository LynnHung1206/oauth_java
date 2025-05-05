package com.lynn.oauth_demo.service;

import com.lynn.oauth_demo.dao.OauthProvidersDao;
import com.lynn.oauth_demo.vo.OauthProvidersVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: Lynn on 2025/5/3
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OauthProviderService {

  private final OauthProvidersDao oauthProvidersDao;

  public OauthProvidersVo getOauthProviderById(Integer providerId) {
    return oauthProvidersDao.selectById(providerId);
  }
}
