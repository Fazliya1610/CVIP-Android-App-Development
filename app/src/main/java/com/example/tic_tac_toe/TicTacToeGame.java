package com.example.tic_tac_toe;

public class TicTacToeGame {

    private char[][] board;
    private char currentPlayer;
    private boolean gameWon;
    private boolean gameDraw;

    public TicTacToeGame() {
        board = new char[3][3];
        currentPlayer = 'X';
        gameWon = false;
        gameDraw = false;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public boolean makeMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ' || gameWon || gameDraw) {
            return false;
        }
        board[row][col] = currentPlayer;
        gameWon = checkForWin(currentPlayer, row, col);
        if (!gameWon) {
            gameDraw = checkForDraw();
            if (!gameDraw) {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
        return true;
    }

    public boolean makeBotMove() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return makeMove(i, j);
                }
            }
        }
        return false;
    }

    private boolean checkForWin(char player, int row, int col) {
        // Check row
        if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
            return true;
        }
        if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
            return true;
        }
        if (row == col && board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (row + col == 2 && board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    private boolean checkForDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public boolean isGameDraw() {
        return gameDraw;
    }

    public char[][] getBoard() {
        return board;
    }
}
