#  Oauth2.0 client 實作
## Google OAuth2.0 登入
到 Google Cloud Console 設定 OAuth 應用
打開這個網站：https://console.cloud.google.com/

建一個新的專案（New Project）：

點右上角專案列表 → 新增專案。

隨便取一個名字，例如：LoginServiceProject

地區選預設，建立。

在新專案裡面，開啟 OAuth 同意畫面（左側選單 ➔ API 與服務 ➔ OAuth 同意畫面）

選 外部（因為我們是要給真實使用者用，不是內部自己公司用）

填基本資料：

應用名稱：自己取，譬如 SuperLogin

使用者支援電子郵件：選你自己的。

開發人員聯絡資訊：填你的 email。

設定範圍（Scope）：

點「新增或刪除範圍」。

勾選基本資訊（email、profile）。

存起來。

設定測試使用者（因為沒公開發布，只能特定帳號能登入）：

加自己的 Gmail 地址進去。

建立 OAuth 2.0 認證資訊：

左側選單 ➔ API 與服務 ➔ 認證資訊 ➔ 建立憑證 ➔ OAuth 用戶端ID

選擇「網頁應用程式」

命名：例如 LoginServiceWeb

「授權的重新導向 URI」填：http://localhost:8080/api/oauth2/callback

（這個是等一下我們後端接收 Google 回來的跳轉位置 錯了就授權失敗）

建好之後，它會給你一組 Client ID 和 Client Secret 自己留好。

## Github OAuth2.0 登入
到 Github 設定 OAuth 應用 [Settings > Developer settings > OAuth Apps]
一樣保存好自己的 Client ID 和 Client Secret
