package com.github.steven.tdd.ch03;

/**
 * TicTacToe.
 *
 * @author shidingfeng
 */
public class TicTacToe {
    private Character[][] board = {{'\0', '\0', '\0'}, {'\0', '\0', '\0'}, {'\0', '\0', '\0'}};
    private char lastPlayer = '\0';
    private static final int SIZE = 3;

    public String play(int x, int y) {
        checkAxis(x);
        checkAxis(y);
        lastPlayer = nextPlay();
        setBox(x, y);
        if (isWin(x, y)) {
            return lastPlayer + " is the winner";
        }
        return "No winner";
    }

    private void checkAxis(int axis) {
        if (axis < 1 || axis > 3) {
            throw new RuntimeException("X is outside board");
        }
    }

    private void setBox(int x, int y) {
        if (board[x - 1][y - 1] != '\0') {
            throw new RuntimeException("Box is occupied");
        }else {
            board[x - 1][y - 1] = lastPlayer;
        }
    }

    public char nextPlay() {
        if (lastPlayer == 'X') {
            return 'O';
        }
        return 'X';
    }

    private boolean isWin(int x, int y) {
        int playerTotal = lastPlayer * SIZE;
        char horizontal, vertical;
        horizontal = vertical = '\0';
        for (int i = 0; i < SIZE; i++) {
            horizontal += board[i][y - 1];
            vertical += board[x - 1][i];
        }
        if (horizontal == playerTotal
                || vertical == playerTotal) {
            return true;
        }
        return false;
    }
}
