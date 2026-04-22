package com.chess.model;

public class Queen extends Piece {
    public Queen(int row, int col, boolean isWhite) {
        super(row, col, isWhite, "queen");
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol, Piece[][] board) {
        boolean isDiagonal = Math.abs(targetRow - this.row) == Math.abs(targetCol - this.col);
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