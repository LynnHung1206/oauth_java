use test;
INSERT INTO oauth_providers (
    provider_name,
    provider_description,
    client_id,
    client_secret,
    redirect_uri,
    authorization_endpoint,
    token_endpoint,
    userinfo_endpoint,
    scope
) VALUES (
             'github',
             'GitHub OAuth Provider',
             '你的GitHub Client ID',
             '你的GitHub Client Secret',
             'http://localhost:8080/api/oauth2/callback/github',
             'https://github.com/login/oauth/authorize',
             'https://github.com/login/oauth/access_token',
             'https://api.github.com/user',
             'read:user user:email'
         );