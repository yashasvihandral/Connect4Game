import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.util.Arrays; 


public class Connect4Game extends JFrame {

    private final int ROWS = 6;
    private final int COLS = 7;
    private final int CELL_SIZE = 100;
    private final int BOARD_WIDTH = COLS * CELL_SIZE;
    private final int BOARD_HEIGHT = ROWS * CELL_SIZE;

    private final ImageIcon coinImage1; // Image for Player 1's coin
    private final ImageIcon coinImage2; // Image for Player 2's coin
    private final ImageIcon backgroundImage; 

    private int currentPlayer; // 1 or 2 to indicate current player
    private int[][] board; // 0 for empty cell, 1 for player 1's coin, 2 for player 2's coin

    public Connect4Game(ImageIcon coinImage1, ImageIcon coinImage2, ImageIcon backgroundImage) {
        this.coinImage1 = coinImage1;
        this.coinImage2 = coinImage2;
        this.backgroundImage = backgroundImage;

        board = new int[ROWS][COLS];
        currentPlayer = 1;

        setTitle("Connect 4 Game");
        setSize(BOARD_WIDTH, BOARD_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        BoardPanel boardPanel = new BoardPanel();
        add(boardPanel);

        boardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = e.getX() / CELL_SIZE/2;
                dropCoin(col);
            }
        });
    }

    private void dropCoin(int col) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col] == 0) {
                board[row][col] = currentPlayer;
                currentPlayer = currentPlayer == 1 ? 2 : 1;
                repaint();
                break;
            }
        }
    }

    private boolean checkWin(int player) {
        // Check horizontal
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col <= COLS - 4; col++) {
                if (board[row][col] == player && board[row][col + 1] == player &&
                        board[row][col + 2] == player && board[row][col + 3] == player) {
                    return true;
                }
            }
        }

        // Check vertical
        for (int col = 0; col < COLS; col++) {
            for (int row = 0; row <= ROWS - 4; row++) {
                if (board[row][col] == player && board[row + 1][col] == player &&
                        board[row + 2][col] == player && board[row + 3][col] == player) {
                    return true;
                }
            }
        }

        // Check diagonal (top left to bottom right)
        for (int row = 0; row <= ROWS - 4; row++) {
            for (int col = 0; col <= COLS - 4; col++) {
                if (board[row][col] == player && board[row + 1][col + 1] == player &&
                        board[row + 2][col + 2] == player && board[row + 3][col + 3] == player) {
                    return true;
                }
            }
        }

        // Check diagonal (top right to bottom left)
        for (int row = 0; row <= ROWS - 4; row++) {
            for (int col = 3; col < COLS; col++) {
                if (board[row][col] == player && board[row + 1][col - 1] == player &&
                        board[row + 2][col - 2] == player && board[row + 3][col - 3] == player) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private class BoardPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawString("x", 100, 100);
            // Draw background grid
            g.drawImage(backgroundImage.getImage(), 0, 0, BOARD_WIDTH, BOARD_HEIGHT, null);

            // Draw coins
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
                    if (board[row][col] == 1) {
                        g.drawImage(coinImage1.getImage(), col * CELL_SIZE, row * CELL_SIZE/2 , CELL_SIZE*3, CELL_SIZE*3, null);
                    } else if (board[row][col] == 2) {
                        g.drawImage(coinImage2.getImage(), col * CELL_SIZE, row * CELL_SIZE/2,  CELL_SIZE*3, CELL_SIZE*3, null);
                    }
                }
            }

            if (checkWin(1)) {
                JOptionPane.showMessageDialog(this, "Player 1 wins!");
                resetGame();
            } else if (checkWin(2)) {
                JOptionPane.showMessageDialog(this, "Player 2 wins!");
                resetGame();
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(this, "The game is a draw!");
                resetGame();
            }
        }
    }

    private void resetGame() {
        board = new int[ROWS][COLS];
        currentPlayer = 1;
        repaint();
        
    }

 

	public static void main(String[] args) {
        ImageIcon coinImage1 = new ImageIcon("red.png"); 
        ImageIcon coinImage2 = new ImageIcon("yellow.png"); 
        ImageIcon backgroundImage = new ImageIcon("background.JPG");

        SwingUtilities.invokeLater(() -> {
            Connect4Game game = new Connect4Game(coinImage1, coinImage2, backgroundImage);
            game.setVisible(true);
        });
    }
}