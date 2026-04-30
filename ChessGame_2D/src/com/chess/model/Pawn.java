package com.chess.model;

public class Pawn extends Piece {
    public Pawn(int row, int col, boolean isWhite) {
        super(row, col, isWhite, "pawn");
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol, Piece[][] board) {
        // 1. 色によって進む方向を決定
        // 白 (isWhite = true) は 6 行目から 0 行目へ進む → 方向は -1
        // 黒 (isWhite = false) は 1 行目から 7 行目へ進む → 方向は +1
        int direction = isWhite ? -1 : 1;
        
        int rowDiff = targetRow - this.row;
        int colDiff = targetCol - this.col;
        Piece target = board[targetRow][targetCol];

        // --- ケース 1: 前進 ---
        if (colDiff == 0) {
            // 1 マス前進：目的地が空である必要がある
            if (rowDiff == direction && target == null) {
                return true;
            }
            // 2 マス前進：初期位置のみ、かつ途中のマスが空であること
            if (rowDiff == 2 * direction && target == null) {
                if (isWhite && this.row == 6 && board[5][this.col] == null) return true;
                if (!isWhite && this.row == 1 && board[2][this.col] == null) return true;
            }
        }

        // --- ケース 2: 斜めに駒を取る ---
        // 斜め 1 マス、かつ相手の駒がある場合のみ
        if (Math.abs(colDiff) == 1 && rowDiff == direction) {
            if (target != null && target.isWhite != this.isWhite) {
                return true;
            }
        }

        return false;
    }
}
