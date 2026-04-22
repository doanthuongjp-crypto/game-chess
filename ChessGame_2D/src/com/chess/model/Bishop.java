package com.chess.model;

public class Bishop extends Piece {
    public Bishop(int row, int col, boolean isWhite) {
        super(row, col, isWhite, "bishop");
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol, Piece[][] board) {
        // Đi chéo: hiệu dòng = hiệu cột
        if (Math.abs(targetRow - this.row) == Math.abs(targetCol - this.col)) {
            if (isPathClear(targetRow, targetCol, board)) {
                Piece target = board[targetRow][targetCol];
                return target == null || target.isWhite != this.isWhite;
            }
        }
        return false;
    }
}