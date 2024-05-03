import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame {

    private JButton[][] buttons;
    private boolean playerTurn;
    private int[][] board;

    public TicTacToe() {
        setTitle("TicTacToe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setResizable(false);
        setLocationRelativeTo(null);

        // Create the game board
        buttons = new JButton[3][3];
        board = new int[3][3];
        playerTurn = true;

        JPanel gamePanel = new JPanel(new GridLayout(3, 3, 10, 10));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("calbri", Font.BOLD, 70));
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                gamePanel.add(buttons[i][j]);
            }
        }

        // Add the game panel to the frame
        getContentPane().add(gamePanel, BorderLayout.CENTER);

        // Add a reset button
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        getContentPane().add(resetButton, BorderLayout.SOUTH);
    }

    private class ButtonClickListener implements ActionListener {
        private int row, col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (board[row][col] == 0) {
                if (playerTurn) {
                    buttons[row][col].setText("X");
                    board[row][col] = 1;
                } else {
                    buttons[row][col].setText("O");
                    board[row][col] = 2;
                }
                playerTurn = !playerTurn;
                checkWin();
            }
        }
    }

    private void checkWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != 0) {
                showWinner(board[i][0]);
                return;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != 0) {
                showWinner(board[0][i]);
                return;
            }
        }

        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != 0) {
            showWinner(board[0][0]);
            return;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != 0) {
            showWinner(board[0][2]);
            return;
        }

        // Check for a tie
        boolean tie = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    tie = false;
                    break;
                }
            }
        }
        if (tie) {
            JOptionPane.showMessageDialog(this, "It is a tie!");
            resetGame();
        }
    }

    private void showWinner(int player) {
        if (player == 1) {
            JOptionPane.showMessageDialog(this, "Player X wins!");
        } else {
            JOptionPane.showMessageDialog(this, "Player O wins!");
        }
        resetGame();
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                board[i][j] = 0;
            }
        }
        playerTurn = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TicTacToe().setVisible(true);
            }
        });
    }
}