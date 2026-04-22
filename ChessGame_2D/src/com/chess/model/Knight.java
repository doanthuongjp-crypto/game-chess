package com.chess.model;

public class Knight extends Piece {
    public Knight(int row, int col, boolean isWhite) {
        super(row, col, isWhite, "knight");
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol, Piece[][] board) {
        int rowDiff = Math.abs(targetRow - this.row);
        int colDiff = Math.abs(targetCol - this.col);
        
        if (rowDiff * colDiff == 2) { // Logic hình chữ L
            Piece target = board[targetRow][targetCol];
            return target == null || target.isWhite != this.isWhite;
        }
        return false;
    }
}