use test;
ALTER
DATABASE test
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS members (
    member_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '會員ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '會員名稱',
    email VARCHAR(100) NOT NULL UNIQUE COMMENT '會員電子郵件',
    password_hash VARCHAR(255),
    full_name VARCHAR(100) COMMENT '全名',
    phone_number VARCHAR(20) COMMENT '電話號碼',
    registration_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '註冊日期',
    last_login DATETIME DEFAULT NULL COMMENT '最後登入時間',
    account_status ENUM('active', 'inactive', 'suspended', 'deleted') NOT NULL DEFAULT 'active' COMMENT '帳號狀態',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間'
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS oauth_providers (
    provider_id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'OAuth提供者ID',
    provider_name VARCHAR(50) NOT NULL UNIQUE COMMENT 'OAuth提供者名稱',
    provider_description VARCHAR(255) COMMENT 'OAuth提供者描述',
    client_id VARCHAR(255) NOT NULL COMMENT 'OAuth提供者分配的應用ID(建議加密存儲)',
    client_secret VARCHAR(255) NOT NULL COMMENT 'OAuth提供者分配的應用密鑰(建議加密存儲)',
    redirect_uri VARCHAR(255) NOT NULL COMMENT 'OAuth回調URL',
    scope VARCHAR(255) COMMENT 'OAuth請求的範圍',
    authorization_endpoint VARCHAR(255) COMMENT '授權端點URL',
    token_endpoint VARCHAR(255) COMMENT '令牌端點URL',
    userinfo_endpoint VARCHAR(255) COMMENT '用戶信息端點URL',
    is_active BOOLEAN NOT NULL DEFAULT TRUE COMMENT '是否啟用',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間'
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_unicode_ci;

-- 會員OAuth登入資訊表
CREATE TABLE IF NOT EXISTS member_oauth (
    oauth_id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'OAuth ID',
    member_id INT NOT NULL COMMENT '會員ID',
    provider_id INT NOT NULL COMMENT 'OAuth提供者ID',
    oauth_sub VARCHAR(255) NOT NULL COMMENT 'OAuth提供者的唯一識別碼',
    oauth_token TEXT COMMENT 'OAuth令牌（可加密存儲）',
    oauth_refresh_token TEXT COMMENT 'OAuth刷新令牌（可加密存儲）',
    token_expiry DATETIME COMMENT '令牌過期時間',
    oauth_email VARCHAR(100) COMMENT 'OAuth提供者的電子郵件',
    oauth_name VARCHAR(100) COMMENT 'OAuth提供者的名稱',
    oauth_profile_picture VARCHAR(255) COMMENT 'OAuth提供者的頭像URL',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '創建時間',
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
    UNIQUE KEY unique_oauth_provider_sub (provider_id, oauth_sub),
    FOREIGN KEY (member_id) REFERENCES members(member_id) ON DELETE CASCADE,
    FOREIGN KEY (provider_id) REFERENCES oauth_providers(provider_id) ON DELETE CASCADE,
    INDEX idx_member_id (member_id),
    INDEX idx_oauth_sub (oauth_sub)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_unicode_ci;

-- 登入歷史記錄表
CREATE TABLE IF NOT EXISTS login_history (
    login_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '登入ID',
    member_id INT NOT NULL COMMENT '會員ID',
    login_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登入時間',
    login_ip VARCHAR(45) COMMENT '登入IP地址',
    login_device VARCHAR(255) COMMENT '登入設備',
    login_method ENUM('password', 'oauth') NOT NULL COMMENT '登入方式',
    provider_id INT NULL COMMENT 'OAuth提供者ID',
    login_status ENUM('success', 'failed') NOT NULL COMMENT '登入狀態',
    failure_reason VARCHAR(255) COMMENT '失敗原因',
    FOREIGN KEY (member_id) REFERENCES members(member_id) ON DELETE CASCADE,
    FOREIGN KEY (provider_id) REFERENCES oauth_providers(provider_id) ON DELETE SET NULL,
    INDEX idx_member_login (member_id, login_time)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_unicode_ci;