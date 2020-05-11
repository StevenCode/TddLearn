package com.github.steven.tdd.ch03;

/**
 * TicTacToe.
 *
 * @author shidingfeng
 */
public class TicTacToe {
    private Character[][] board = {{'\0', '\0', '\0'}, {'\0', '\0', '\0'}, {'\0', '\0', '\0'}};
    private char lastPlayer = '\0';

    public void play(int x, int y) {
        checkAxis(x);
        checkAxis(y);
        setBox(x, y);
        lastPlayer = nextPlay();
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
            board[x - 1][y - 1] = 'X';
        }
    }

    public char nextPlay() {
        if (lastPlayer == 'X') {
            return 'O';
        }
        return 'X';
    }
}
