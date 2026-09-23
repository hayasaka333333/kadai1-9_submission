# テスト理論 課題1〜9 提出用

このフォルダは、配布PDFに記載された課題1〜9のテストコードを提出用にまとめたものです。
各ベースプロジェクトへ、同じ相対パスで上書きまたは追加して使用してください。

## 提出物対応表

| 課題 | 提出ファイル |
|---|---|
| 1, 2 | `kadai01-02-junit-test01/src/test/java/com/example/CalcTest.java` |
| 3 | `kadai03-05-TestTheory2/src/test/java/com/example/demo/UserTest.java` |
| 4, 5 | `kadai03-05-TestTheory2/src/test/java/com/example/demo/UserManagerTest.java` |
| 6 | `kadai06-springtest01-restapi/src/test/java/com/example/HelloControllerTest.java` |
| 7 | `kadai07-springtest02-thymeleaf/src/test/java/com/example/UserControllerTest.java` と `pom.xml` |
| 8 | `kadai08-springtest03-db/src/test/java/com/example/BookControllerTest.java`、SQL、`pom.xml` |
| 9 | `kadai09-springtest04-security/src/test/java/com/example/SecurityAccessTest.java` と `pom.xml` |

## 課題ごとの内容

### 課題1
`Calc.sub`、`Calc.div`、`Calc.mult` の正常系を AssertJ で検証します。

### 課題2
`Calc.div(x, 0)` が `ArithmeticException` を発生させ、例外メッセージに `by zero` を含むことを検証します。

### 課題3
`User` の管理コード、名前、年齢の登録参照、範囲外年齢の扱い、連続操作時の範囲外年齢を検証します。

### 課題4
`UserManager` の Singleton、List/Map 登録、全削除、管理コード指定削除を検証します。
異常系は、同値だが別インスタンスの `String` を削除キーに使い、文字列を `==` で比較する実装不具合も検出できる形にしています。

### 課題5
List/Map の初期状態、List の登録順、Map のキーがユーザー管理コードであることを検証します。

### 課題6
`GET /hello` が正常応答し、`Hello, Spring Boot!` を返すことを MockMvc で検証します。

### 課題7
初期2名がいる状態で1名を追加し、氏名、メールアドレス、部門が画面に出ることを検証します。
別テストで初期2名を削除し、一覧が0件になり、空表示メッセージが出ることを検証します。

### 課題8
`@Sql` で3冊を投入し、`GET /books` のモデルに3件入り、タイトルが投入順に一致することを検証します。

### 課題9
ベースプロジェクトのセキュリティ設定に合わせ、`/login` と `/register` は未認証でも表示できること、`/books` は未認証時にログイン画面へ移動すること、USER/ADMIN の認証済み利用者は `/books` を表示できることを検証します。

## 注意事項

課題3〜5で参照する `User.java` と `UserManager.java` は、PDFでは「配布したファイルを配置する」とだけ記載され、今回アップロードされたPDF群にはソース本体が含まれていません。そのため、テストはPDFに書かれた公開メソッド名と仕様だけに合わせています。範囲外年齢は確実に通常範囲を外れる入力として `Integer.MAX_VALUE` を使っています。

課題7〜9の `pom.xml` は、PDFから参照されるベースリポジトリの現在の Spring Boot 4.1.0 構成に合わせています。Spring Boot 4 系で MockMvc テストを使うため、`spring-boot-starter-webmvc-test` を追加しています。課題9では Spring Security の MockMvc 支援用に `spring-security-test` も追加しています。

課題7の現行ベースリポジトリの起動クラス名は `Application` です。手元の配布版がスライド記載どおり `App` の場合は、`UserControllerTest.java` の `classes = Application.class` を `classes = App.class` に変更してください。

## 実行

各プロジェクトのルートで次を実行します。

```bash
mvn test
```

VS Code の Testing から各テストクラスを実行しても構いません。
