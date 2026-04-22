package com.chess.model;

public class Pawn extends Piece {
    public Pawn(int row, int col, boolean isWhite) {
        super(row, col, isWhite, "pawn");
    }

    @Override
    public boolean isValidMove(int targetRow, int targetCol, Piece[][] board) {
        // 1. Xác định hướng đi dựa trên màu sắc
        // Trắng (isWhite = true) đi từ dòng 6 về dòng 0 => hướng là -1
        // Đen (isWhite = false) đi từ dòng 1 về dòng 7 => hướng là +1
        int direction = isWhite ? -1 : 1;
        
        int rowDiff = targetRow - this.row;
        int colDiff = targetCol - this.col;
        Piece target = board[targetRow][targetCol];

        // --- TRƯỜNG HỢP 1: ĐI THẲNG ---
        if (colDiff == 0) {
            // Đi 1 ô: Ô đích phải trống
            if (rowDiff == direction && target == null) {
                return true;
            }
            // Đi 2 ô: Chỉ khi đang ở vị trí xuất phát và đường đi trống
            if (rowDiff == 2 * direction && target == null) {
                if (isWhite && this.row == 6 && board[5][this.col] == null) return true;
                if (!isWhite && this.row == 1 && board[2][this.col] == null) return true;
            }
        }

        // --- TRƯỜNG HỢP 2: ĂN QUÂN CHÉO ---
        // Đi chéo 1 ô và ô đó phải có quân đối phương
        if (Math.abs(colDiff) == 1 && rowDiff == direction) {
            if (target != null && target.isWhite != this.isWhite) {
                return true;
            }
        }

        return false;
    }
}