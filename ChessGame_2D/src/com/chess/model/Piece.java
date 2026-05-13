package com.chess.model;

import java.awt.Image;
import java.net.URL; 
import javax.imageio.ImageIO;

/**
 * 駒の基本情報を管理する抽象クラス
 */
public abstract class Piece {
    public int row, col;        // 現在の位置 (0-7)
    public boolean isWhite;     // 先手/白 (true) または 後手/黒 (false)
    public String name;         // 駒の名前 (pawn, rook, knightなど)
    public Image img;           // 駒の画像データ

    public Piece(int row, int col, boolean isWhite, String name) {
        this.row = row;
        this.col = col;
        this.isWhite = isWhite;
        this.name = name;
        this.img = loadImage(); 
    }

    /**
     * リソースフォルダから駒の画像を読み込む
     */
    private Image loadImage() {
        try {
            // 色に基づいた接頭辞の決定 (白は "w_", 黒は "b_")
            String colorPrefix = isWhite ? "w_" : "b_";
            
            // ソースフォルダからの相対パスを生成
            String relativePath = "/" + colorPrefix + name.toLowerCase() + ".png";
            
            // クラスパス内からリソースURLを取得
            URL imageUrl = getClass().getResource(relativePath);
            
            if (imageUrl != null) {
                return ImageIO.read(imageUrl);
            } else {
                System.err.println("エラー: リソースが見つかりません: " + relativePath);
                return null;
            }
        } catch (Exception e) {
            System.err.println("画像読み込みエラー: " + e.getMessage());
            return null;
        }
    }

    /**
     * 移動経路に他の駒が存在しないかを確認する
     */
    protected boolean isPathClear(int targetRow, int targetCol, Piece[][] board) {
        // 移動方向を決定 (-1, 0, 1)
        int rowStep = Integer.compare(targetRow, this.row);
        int colStep = Integer.compare(targetCol, this.col);

        int currRow = this.row + rowStep;
        int currCol = this.col + colStep;

        // 目標地点に到達するまで、経路上のマスをチェック
        while (currRow != targetRow || currCol != targetCol) {
            if (board[currRow][currCol] != null) {
                return false; // 途中に駒がある場合は移動不可
            }
            currRow += rowStep;
            currCol += colStep;
        }
        return true;
    }

    /**
     * 各駒の固有の移動ルールに基づいたバリデーション (サブクラスで実装)
     */
    public abstract boolean isValidMove(int targetRow, int targetCol, Piece[][] board);
}
