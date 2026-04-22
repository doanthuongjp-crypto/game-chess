package com.chess.main;

import javax.swing.JFrame;

import com.chess.controller.ChessController;
import com.chess.model.Bishop;
import com.chess.model.King;
import com.chess.model.Knight;
import com.chess.model.Pawn;
import com.chess.model.Piece;
import com.chess.model.Queen;
import com.chess.model.Rook;
import com.chess.view.GamePanel;

public class Main {
    public static void main(String[] args) {
        Piece[][] board = new Piece[8][8];
        setupStartingPosition(board); // Hàm hỗ trợ đặt quân cờ ban đầu

        JFrame window = new JFrame("Java Chess 2D - Full Version");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GamePanel gamePanel = new GamePanel(board);
        window.add(gamePanel);

        ChessController controller = new ChessController(board, gamePanel);
        gamePanel.addMouseListener(controller);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    private static void setupStartingPosition(Piece[][] board) {
        //Black
        board[0][0] = new Rook(0, 0, false);
        board[0][7] = new Rook(0, 7, false);
        board[0][1] = new Knight(0, 1, false);
        board[0][6] = new Knight(0, 6, false);
        board[0][2] = new Bishop(0, 2, false);
        board[0][5] = new Bishop(0, 5, false);
        board[0][3] = new Queen(0, 3, false);
        board[0][4] = new King(0, 4, false);
        for (int i = 0; i < 8; i++) board[1][i] = new Pawn(1, i, false);

        // White
        board[7][0] = new Rook(7, 0, true);
        board[7][7] = new Rook(7, 7, true);
        board[7][1] = new Knight(7, 1, true);
        board[7][6] = new Knight(7, 6, true);
        board[7][2] = new Bishop(7, 2, true);
        board[7][5] = new Bishop(7, 5, true);
        board[7][3] = new Queen(7, 3, true);
        board[7][4] = new King(7, 4, true);
        for (int i = 0; i < 8; i++) board[6][i] = new Pawn(6, i, true);
    }
}
