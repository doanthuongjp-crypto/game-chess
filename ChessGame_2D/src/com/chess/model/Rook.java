package com.chess.model;

public class Queen extends Piece {
    public Queen(int row, int col, boolean isWhite) {
        super(row, col, isWhite, "queen");
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol, Piece[][] board) {
        // 斜め移動かどうか
        boolean isDiagonal = Math.abs(targetRow - this.row) == Math.abs(targetCol - this.col);
        // 縦または横の直線移動かどうか
        boolean isStraight = (this.row == targetRow || this.col == targetCol);

        if (isDiagonal || isStraight) {
            if (isPathClear(targetRow, targetCol, board)) {
                Piece target = board[targetRow][targetCol];
                return target == null || target.isWhite != this.isWhite;
            }
        }
        return false;
    }
}
