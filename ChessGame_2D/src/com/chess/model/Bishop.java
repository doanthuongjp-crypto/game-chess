package com.chess.model;

public class Bishop extends Piece {
    public Bishop(int row, int col, boolean isWhite) {
        super(row, col, isWhite, "bishop");
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol, Piece[][] board) {
        // 斜めに移動：行の差 = 列の差
        if (Math.abs(targetRow - this.row) == Math.abs(targetCol - this.col)) {
            if (isPathClear(targetRow, targetCol, board)) {
                Piece target = board[targetRow][targetCol];
                return target == null || target.isWhite != this.isWhite;
            }
        }
        return false;
    }
}
