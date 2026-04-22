package com.chess.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.chess.model.Piece;

public class GamePanel extends JPanel {
    private final int TILE_SIZE = 80;
    private Piece[][] board;
    
    // --- BIẾN HIỆU ỨNG ---
    public Point selectedSquare = null; // Ô đang được chọn (x=col, y=row)
    public List<Point> validMoves = new ArrayList<>(); // Các ô gợi ý

    public GamePanel(Piece[][] board) {
        this.board = board;
        this.setPreferredSize(new Dimension(8 * TILE_SIZE, 8 * TILE_SIZE));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawHighlights(g); // Vẽ hiệu ứng
        drawPieces(g);
    }

    private void drawBoard(Graphics g) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                g.setColor((row + col) % 2 == 0 ? new Color(235, 235, 208) : new Color(119, 148, 85));
                g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    private void drawHighlights(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        
        // 1. Tô màu vàng ô đang được chọn
        if (selectedSquare != null) {
            g2.setColor(new Color(255, 255, 0, 120)); // Vàng trong suốt
            g2.fillRect(selectedSquare.x * TILE_SIZE, selectedSquare.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

        // 2. Vẽ dấu chấm tròn cho các nước đi hợp lệ
        g2.setColor(new Color(0, 0, 0, 60)); // Đen mờ
        for (Point p : validMoves) {
            // Vẽ vòng tròn nhỏ ở tâm ô
            int centerX = p.x * TILE_SIZE + TILE_SIZE / 2;
            int centerY = p.y * TILE_SIZE + TILE_SIZE / 2;
            g2.fillOval(centerX - 10, centerY - 10, 20, 20);
        }
    }

    private void drawPieces(Graphics g) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece p = board[row][col];
                if (p != null && p.img != null) {
                    g.drawImage(p.img, col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                }
            }
        }
    }
}