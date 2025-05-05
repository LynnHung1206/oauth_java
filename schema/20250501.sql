use test;
INSERT INTO oauth_providers (
    provider_name,
    provider_description,
    client_id,
    client_secret,
    redirect_uri,
    scope,
    authorization_endpoint,
    token_endpoint,
    userinfo_endpoint
) VALUES
    ('google', 'Google OAuth登入',
     '您的Google客戶端ID', '您的Google客戶端密鑰',
     'http://localhost:8080/api/oauth2/callback',
     'openid profile email',
     'https://accounts.google.com/o/oauth2/auth',
     'https://oauth2.googleapis.com/token',
     'https://www.googleapis.com/oauth2/v3/userinfo');