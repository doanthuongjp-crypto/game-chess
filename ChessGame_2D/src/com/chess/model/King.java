package com.chess.model;

public class King extends Piece {
    public King(int row, int col, boolean isWhite) {
        super(row, col, isWhite, "king");
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol, Piece[][] board) {
        int rowDiff = Math.abs(targetRow - this.row);
        int colDiff = Math.abs(targetCol - this.col);

        // キングは上下左右および斜めに1マスだけ移動できる
        if (rowDiff <= 1 && colDiff <= 1) {
            Piece target = board[targetRow][targetCol];
            return target == null || target.isWhite != this.isWhite;
        }
        return false;
    }
}
