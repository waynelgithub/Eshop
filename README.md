# Eshop - RWD 服飾購物網站
## 介紹
* 以 MVC 模式開發的 RWD 服飾類購物 Web App
* 使用 Spring MVC + Thymeleaf 為框架
* 使用 Spring Security, 以 RBAC model 管理會員驗證及授權，以及網站安全性
* 使用 Hibernate 搭配 Spring Data JPA 管理資料存取
* 使用 Bootstrap 及 Jquery 管理前端互動
## 使用說明
* 資料庫連線資訊可在 [database.properties](https://github.com/waynelgithub/Eshop/blob/master-github/src/main/resources/database.properties) 修改
* 商品資料及圖檔 SQL 可在此[SQL](https://github.com/waynelgithub/Eshop/tree/master-github/WebContent/WEB-INF/resources)取得，可先在資料庫建立一批測試資料
* 目前專案內 CSRF token 為關閉狀態，請自行打開

## 架構
* 前端：Thymeleaf、 Jquery、 Bootstrap
* 後端：Spring MVC、Spring Security、Spring Data、Hibernate、Log4j 2
* OS: Linux
* AP Server: Tomcat
* DB: MySQL
* Project: Maven, Eclipse based

## 功能
### 商品上下架管理
### 帳號註冊管理
### 購物車機制
### 訂單管理機制
### 退貨管理機制
### 驗證及授權管理機制
### 後台人員管理機制

