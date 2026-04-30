package com.chess.controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import com.chess.model.King; // 重要: 勝利条件を確認するために King クラスをインポートする必要がある
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

        // 盤面の範囲外をクリックした場合は無視
        if (row < 0 || row >= 8 || col < 0 || col >= 8) return;

        if (selectedPiece == null) {
            // --- 1回目のクリック: 駒を選択 ---
            Piece p = board[row][col];
            if (p != null && p.isWhite == whiteTurn) {
                selectedPiece = p;
                
                // ビューに選択状態を反映
                view.selectedSquare = new Point(col, row);
                view.validMoves.clear();
                
                // 盤面全体を走査して移動可能マスを取得
                for (int r = 0; r < 8; r++) {
                    for (int c = 0; c < 8; c++) {
                        if (selectedPiece.isValidMove(r, c, board)) {
                            view.validMoves.add(new Point(c, r));
                        }
                    }
                }
            }
        } else {
            // --- 2回目のクリック: 移動または駒取り ---
            if (selectedPiece.isValidMove(row, col, board)) {
                Piece targetPiece = board[row][col];

                // 勝利条件チェック（キングを取った場合）
                if (targetPiece instanceof King) {
                    executeMove(row, col);
                    view.repaint(); // メッセージ表示前に描画更新
                    
                    String winner = whiteTurn ? "白" : "黒";
                    JOptionPane.showMessageDialog(view, 
                        "キングが取られました！ " + winner + " の勝利です！", 
                        "ゲーム終了", 
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    System.exit(0); // プログラム終了
                    return;
                }

                // 通常の移動処理
                executeMove(row, col);
                whiteTurn = !whiteTurn; // 手番交代
            }
            
            // 移動後（または誤クリック後）の選択解除
            selectedPiece = null;
            view.selectedSquare = null;
            view.validMoves.clear();
        }
        view.repaint(); 
    }

    /**
     * 駒の位置を board 配列に反映する補助メソッド
     */
    private void executeMove(int row, int col) {
        board[selectedPiece.row][selectedPiece.col] = null;
        selectedPiece.row = row;
        selectedPiece.col = col;
        board[row][col] = selectedPiece;
    }
}
