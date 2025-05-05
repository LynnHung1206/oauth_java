package com.lynn.oauth_demo.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import com.baomidou.mybatisplus.annotation.TableName;

/**
* oauth_providers 的 Vo
*/
@Getter
@Setter
@TableName("oauth_providers")
public class  OauthProvidersVo {
    /** 授權端點URL */
    private String authorizationEndpoint;
    /** OAuth提供者分配的應用ID(建議加密存儲) */
    private String clientId;
    /** OAuth提供者分配的應用密鑰(建議加密存儲) */
    private String clientSecret;
    /** 創建時間 */
    private java.time.LocalDateTime createdAt;
    /** 是否啟用 */
    private Integer isActive;
    /** OAuth提供者描述 */
    private String providerDescription;
    /** OAuth提供者ID */
    @TableId
    private Integer providerId;
    /** OAuth提供者名稱 */
    private String providerName;
    /** OAuth回調URL */
    private String redirectUri;
    /** OAuth請求的範圍 */
    private String scope;
    /** 令牌端點URL */
    private String tokenEndpoint;
    /** 更新時間 */
    private java.time.LocalDateTime updatedAt;
    /** 用戶信息端點URL */
    private String userinfoEndpoint;

}
