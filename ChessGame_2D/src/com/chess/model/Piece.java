package com.chess.model;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public abstract class Piece {
    public int row, col;        // Vị trí hiện tại trên bàn cờ (0-7)
    public boolean isWhite;     // Phe trắng (true) hoặc phe đen (false)
    public String name;         // Tên quân cờ (pawn, rook, knight...)
    public Image img;           // Hình ảnh quân cờ

    public Piece(int row, int col, boolean isWhite, String name) {
        this.row = row;
        this.col = col;
        this.isWhite = isWhite;
        this.name = name;
        this.img = loadImage(); // Tải ảnh ngay khi khởi tạo quân cờ
    }

    /**
     * Hàm tải hình ảnh từ đường dẫn tuyệt đối C:\chess\ChessGame_2D\res
     */
    private Image loadImage() {
        try {
            // Xác định màu sắc
            String colorPrefix = isWhite ? "w_" : "b_";
            
            // Xây dựng đường dẫn tuyệt đối đến file ảnh
            // name.toLowerCase() để đảm bảo khớp với file w_pawn.png, b_rook.png...
            String fullPath = "C:\\chess\\ChessGame_2D\\res\\" + colorPrefix + name.toLowerCase() + ".png";
            
            File imgFile = new File(fullPath);
            
            if (imgFile.exists()) {
                return ImageIO.read(imgFile);
            } else {
                System.err.println("LỖI: Không tìm thấy file tại: " + fullPath);
                return null;
            }
        } catch (Exception e) {
            System.err.println("LỖI khi đọc ảnh: " + e.getMessage());
            return null;
        }
    }

    /** Hàm hỗ trợ kiểm tra đường đi trống (dùng cho Xe, Tịnh, Hậu)
     */
    protected boolean isPathClear(int targetRow, int targetCol, Piece[][] board) {
        int rowStep = Integer.compare(targetRow, this.row);
        int colStep = Integer.compare(targetCol, this.col);

        int currRow = this.row + rowStep;
        int currCol = this.col + colStep;

        while (currRow != targetRow || currCol != targetCol) {
            if (board[currRow][currCol] != null) {
                return false; // Bị chặn
            }
            currRow += rowStep;
            currCol += colStep;
        }
        return true;
    }

    /**
     * Luật di chuyển cụ thể của từng loại quân (trừu tượng)
     */
    public abstract boolean isValidMove(int targetRow, int targetCol, Piece[][] board);
}
