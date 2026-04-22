package com.chess.model;

public class Rook extends Piece {
    public Rook(int row, int col, boolean isWhite) {
        super(row, col, isWhite, "rook");
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol, Piece[][] board) {
        // Chỉ đi thẳng hoặc ngang
        if (this.row == targetRow || this.col == targetCol) {
            // Kiểm tra vật cản trên đường đi
            if (isPathClear(targetRow, targetCol, board)) {
                // Kiểm tra ô đích: trống hoặc là quân đối phương
                Piece target = board[targetRow][targetCol];
                return target == null || target.isWhite != this.isWhite;
            }
        }
        return false;
    }
}