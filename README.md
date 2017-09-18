# 運転免許証番号チェックツール

このツールは、日本国内の運転免許証の番号が正しいものか検証するものです。

## 実装前にしていただくこと

このプロジェクトをフォークし、メンバーに `idom-ehr-career` を Reporter として追加して下さい。

※ メンバーの追加は、画面上部のナビゲーションの Members タブから行えます。

## 実装について

あなたのスキルが最大限表現できるように実装して下さい。

### 要求

ユーザーが運転免許証の番号が入力すると、番号に不正がないか分かるように結果を返して下さい。

### 仕様

- I/O は CLI でもブラウザでも自由です。
- 言語、フレームワークは自由に選定して下さい。
- ビジネスロジックに関する部分以外は、ライブラリの使用も自由です。
- 実行環境はどこでも動かせるように Docker で構築して下さい。

以上。

----

## docker-composeについて
docker-compose.yml をアプリケーションルートに配備してあります。
DockerComposeのプロジェクト名が長い場合に、MySQLコンテナがbuffer overflowしてしまいます。 [参考](https://github.com/docker-library/mysql/issues/243)
その回避策として下記のように `-p` オプションを使いDockerComposeが生成するコンテナ名のプレフィックスを短くする必要があります。

```bash
docker-compose -p dlnv up --build
```

### デバッグ用 docker-compose
リモートデバッグ用に5005番ポートを開けたコンテナをたてる `docker-compose-dev.yml` を配備しています。

----

## 運転免許証検証ツール
ツールのURLは `http://localhost:8080/license/validation` です。