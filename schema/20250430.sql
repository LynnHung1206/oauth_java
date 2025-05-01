use test;
ALTER
DATABASE test
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS members (
    member_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255),  -- 可為NULL,若使用OAuth登入
    full_name VARCHAR(100),
    phone_number VARCHAR(20),
    registration_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_login DATETIME,
    account_status ENUM('active', 'inactive', 'suspended', 'deleted') NOT NULL DEFAULT 'active',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS oauth_providers (
    provider_id INT AUTO_INCREMENT PRIMARY KEY,
    provider_name VARCHAR(50) NOT NULL UNIQUE,  -- 例如: google, facebook, github, line
    provider_description VARCHAR(255),
    client_id VARCHAR(255) NOT NULL,           -- OAuth提供者分配的應用clientId
    client_secret VARCHAR(255) NOT NULL,       -- OAuth提供者分配的應用密鑰(建議加密存儲)
    redirect_uri VARCHAR(255) NOT NULL,        -- OAuth回調地址
    scope VARCHAR(255),                        -- 請求的權限範圍
    authorization_endpoint VARCHAR(255),       -- 授權端點URL
    token_endpoint VARCHAR(255),               -- 令牌端點URL
    userinfo_endpoint VARCHAR(255),            -- 用戶信息端點URL
    is_active BOOLEAN NOT NULL DEFAULT TRUE,   -- 是否啟用此提供者
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_unicode_ci;

-- 會員OAuth登入資訊表
CREATE TABLE IF NOT EXISTS member_oauth (
    oauth_id INT AUTO_INCREMENT PRIMARY KEY,
    member_id INT NOT NULL,
    provider_id INT NOT NULL,
    oauth_sub VARCHAR(255) NOT NULL,  -- OAuth提供者給的唯一識別碼(subject)
    oauth_token TEXT,  -- OAuth令牌（可加密存儲）
    oauth_refresh_token TEXT,  -- 刷新令牌（可加密存儲）
    token_expiry DATETIME,  -- 令牌過期時間
    oauth_email VARCHAR(100),  -- OAuth提供的email
    oauth_name VARCHAR(100),  -- OAuth提供的名稱
    oauth_profile_picture VARCHAR(255),  -- OAuth提供的頭像URL
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY unique_oauth_provider_sub (provider_id, oauth_sub),  -- 每個提供者下sub必須唯一
    FOREIGN KEY (member_id) REFERENCES members(member_id) ON DELETE CASCADE,
    FOREIGN KEY (provider_id) REFERENCES oauth_providers(provider_id) ON DELETE CASCADE,
    INDEX idx_member_id (member_id),
    INDEX idx_oauth_sub (oauth_sub)
    ) ENGINE=InnoDB;

-- 登入歷史記錄表
CREATE TABLE IF NOT EXISTS login_history (
    login_id INT AUTO_INCREMENT PRIMARY KEY,
    member_id INT NOT NULL,
    login_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    login_ip VARCHAR(45),  -- 支援IPv6
    login_device VARCHAR(255),  -- 設備信息
    login_method ENUM('password', 'oauth') NOT NULL,
    provider_id INT NULL,  -- 若是OAuth登入
    login_status ENUM('success', 'failed') NOT NULL,
    failure_reason VARCHAR(255),  -- 若登入失敗
    FOREIGN KEY (member_id) REFERENCES members(member_id) ON DELETE CASCADE,
    FOREIGN KEY (provider_id) REFERENCES oauth_providers(provider_id) ON DELETE SET NULL,
    INDEX idx_member_login (member_id, login_time)
    ) ENGINE=InnoDB;