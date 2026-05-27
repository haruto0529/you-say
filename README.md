# you-say-aplication

## 概要
講師の先生の名言を当てる穴埋め式クイズアプリです。  
名言の一部が伏せられており、正しい言葉を入力して回答します。

---

## 機能一覧
- 穴埋め式クイズの出題
- 正誤判定
- いいね機能
- ランク機能

---

## 技術スタック

| カテゴリ | 技術 |
|---|---|
| バックエンド | Java / Spring Boot |
| フロントエンド | Thymeleaf / html / css|
| データベース | MySQL |
| バージョン管理 | maven |

---

## ER図
<!-- ER図の画像をここに追加 -->
![ER図](docs/er.png)

---

## スクリーンショット
<!-- スクリーンショットをここに追加 -->
![トップページ](docs/top.png)

---

## セットアップ手順

### 前提条件
- Java 17以上
- MySQL

### 手順
1. リポジトリをクローン
\`\`\`bash

\`\`\`

2. データベースを作成
\`\`\`sql
CREATE DATABASE quiz_app;
\`\`\`

3. `application.properties`を設定
\`\`\`properties
spring.datasource.url=jdbc:mysql://localhost:3306/quiz_app
spring.datasource.username=your_username
spring.datasource.password=your_password
\`\`\`

4. アプリを起動
\`\`\`bash
./mvnw spring-boot:run
\`\`\`
