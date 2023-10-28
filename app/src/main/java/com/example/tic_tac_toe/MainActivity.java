package com.example.tic_tac_toe;

import java.util.*;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    private TicTacToeGame ticTacToeGame;
    private Button[][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ticTacToeGame = new TicTacToeGame();
        buttons = new Button[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int row = i;
                final int col = j;
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onCellClick(row, col);
                    }
                });
            }
        }
        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });
        makeBotMove();
    }

    private void onCellClick(int row, int col) {
        if (ticTacToeGame.makeMove(row, col)) {
            updateUI();
            if (ticTacToeGame.isGameWon()) {
                String winnerMessage = "Player " + ticTacToeGame.getCurrentPlayer() + " wins!";
                displayWinnerMessage(winnerMessage);
            } else if (ticTacToeGame.isGameDraw()) {
                String drawMessage = "Game Draw!";
                displaydrawMessage(drawMessage);
            }
            makeBotMove();
        }
    }

    private void makeBotMove() {
        if (!ticTacToeGame.isGameWon() && !ticTacToeGame.isGameDraw() && ticTacToeGame.getCurrentPlayer() == 'O') {
            // Bot's turn to make a move
            Random random = new Random();
            int row, col;

            do {
                row = random.nextInt(3);
                col = random.nextInt(3);
            } while (!ticTacToeGame.makeMove(row, col));

            updateUI();
            if (ticTacToeGame.isGameWon()) {
                String winnerMessage = "Player " + ticTacToeGame.getCurrentPlayer() + " wins!";
                displayWinnerMessage(winnerMessage);
            } else if (ticTacToeGame.isGameDraw()) {
                String drawMessage = "Game Draw!";
                displaydrawMessage(drawMessage);
            }
        }
    }

    private void restartGame() {
        ticTacToeGame = new TicTacToeGame();
        updateUI();
        TextView winnerTextView = findViewById(R.id.winnerTextView);
        winnerTextView.setText("");
        makeBotMove();
    }

    private void displayWinnerMessage(String message) {
        TextView winnerTextView = findViewById(R.id.winnerTextView);
        winnerTextView.setText(message);
    }

    private void displaydrawMessage(String message) {
        TextView winnerTextView = findViewById(R.id.winnerTextView);
        winnerTextView.setText(message);
    }

    private void updateUI() {
        char[][] board = ticTacToeGame.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(String.valueOf(board[i][j]));
            }
        }
    }
}