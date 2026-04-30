# ♟️ Java 2D Chess Game (Java 21)

Java 21 を基盤とした、オブジェクト指向プログラミング（OOP）の実践的な 2D チェスアプリケーションです。
このプロジェクトは、コードの再利用性と保守性を高めるために **MVC (Model-View-Controller)** アーキテクチャを採用しています。

## 🌟 主な機能 (Key Features)
*   **本格的なチェスロジック:** 6 種類の駒（King, Queen, Bishop, Knight, Rook, Pawn）の移動規則を網羅。
*   **MVC デザインパターン:** ロジック（Model）、表示（View）、制御（Controller）を明確に分離。
*   **OOP の徹底活用:** 抽象クラス `Piece` を用いた継承と多態性（Polymorphism）の実装。
*   **GUI インタフェース:** Java Swing / AWT を使用したリアルタイムな描画処理。

## 🏗️ プロジェクト構造 (Project Structure)
`image_f0a763.png` で示されている通り、以下のパッケージ構成で整理されています:

*   **`com.chess.model`**: ゲームの核となるデータと論理（駒の動きなど）を管理。
    *   `Piece.java`: 全ての駒の基底となる抽象クラス。
*   **`com.chess.view`**: ユーザーへの表示を担当。`GamePanel.java` で盤面を描画。
*   **`com.chess.controller`**: ユーザーの入力を受け取り、モデルとビューを制御。
*   **`com.chess.main`**: アプリケーションの起動エントリポイント。

## 🛠️ 技術スタック (Tech Stack)
*   **言語:** Java 21
*   **GUI フレームワーク:** Java Swing, AWT
*   **開発環境:** Eclipse (推奨) / IntelliJ IDEA

## 🚀 セットアップと実行方法 (Getting Started)

1. **環境準備:** **JDK 21** 以上がインストールされていることを確認してください。
2. **リポジトリをクローン:**
   ```bash
   git clone [https://github.com/your-username/ChessGame_2D.git](https://github.com/your-username/ChessGame_2D.git)

3. **プロジェクトの取り込み**: お使いの IDE（Eclipse など）にインポートしてください。

4. **実行**: src/com/chess/main/Main.java を実行します。

📸 **デモ** (Screenshots)
起動画面	                         プレイ中の様子
