package com.chess.controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.chess.model.Piece;
import com.chess.view.GamePanel;

public class ChessController extends MouseAdapter {
    private Piece[][] board;
    private GamePanel view;
    private Piece selectedPiece = null;
    private boolean whiteTurn = true;
    private final int TILE_SIZE = 80;

    public ChessController(Piece[][] board, GamePanel view) {
        this.board = board;
        this.view = view;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int col = e.getX() / TILE_SIZE;
        int row = e.getY() / TILE_SIZE;

        if (row < 0 || row >= 8 || col < 0 || col >= 8) return;

        if (selectedPiece == null) {
            // --- LẦN CLICK 1: CHỌN QUÂN ---
            Piece p = board[row][col];
            if (p != null && p.isWhite == whiteTurn) {
                selectedPiece = p;
                
                // CẬP NHẬT HIỆU ỨNG VÀO VIEW
                view.selectedSquare = new Point(col, row); // Lưu ô đang chọn
                view.validMoves.clear();
                
                // Quét toàn bộ bàn cờ để tìm các ô đi được
                for (int r = 0; r < 8; r++) {
                    for (int c = 0; c < 8; c++) {
                        if (selectedPiece.isValidMove(r, c, board)) {
                            view.validMoves.add(new Point(c, r));
                        }
                    }
                }
            }
        } else {
            // --- LẦN CLICK 2: DI CHUYỂN ---
            if (selectedPiece.isValidMove(row, col, board)) {
                board[selectedPiece.row][selectedPiece.col] = null;
                selectedPiece.row = row;
                selectedPiece.col = col;
                board[row][col] = selectedPiece;
                whiteTurn = !whiteTurn;
            }
            
            // RESET HIỆU ỨNG SAU KHI ĐI (HOẶC CLICK SAI)
            selectedPiece = null;
            view.selectedSquare = null;
            view.validMoves.clear();
        }
        view.repaint(); // Bắt buộc gọi repaint để hiện hiệu ứng
    }
}