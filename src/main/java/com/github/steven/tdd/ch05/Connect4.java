package com.github.steven.tdd.ch05;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Pattern;

/**
 * Connect4.
 *
 * @author shidingfeng
 */
public class Connect4 {
    public enum Color{
        RED('R'), GREEN('G'), EMPTY(' ');

        private final char value;

        Color(char value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    public static final int COLUMNS = 7;

    public static final int ROWS = 6;

    private static final String DELIMITER = "|";

    public static final int DISCS_FOR_WIN = 4;

    private Color[][] board = new Color[COLUMNS][ROWS];

    private Color currentPlayer = Color.GREEN;

    private Color winner;

    public Connect4() {
        for (Color[] column : board) {
            Arrays.fill(column, Color.EMPTY);
        }
    }

    private void putDisc(int column) {
        if (column > 0 && column <= COLUMNS) {
            int numOfDiscs = getNumberOfDiscsInColumn(column - 1);
            if (numOfDiscs < ROWS) {
                board[column - 1][numOfDiscs] = currentPlayer;
                printBoard();
                checkWinCondition(column - 1, numOfDiscs);
                switchPlayer();
            }else {
                System.out.println(numOfDiscs);
                System.out.println("There's no room for a new disc in this column");
                printBoard();
            }
        }
    }

    private int getNumberOfDiscsInColumn(int column) {
        if (column >= 0 && column < COLUMNS) {
            int row;
            for (row = 0; row < ROWS; row++) {
                if (Color.EMPTY == board[column][row]) {
                    return row;
                }
            }
            return row;
        }
        return -1;
    }

    private void switchPlayer() {
        if (Color.RED == currentPlayer) {
            currentPlayer = Color.GREEN;
        }else {
            currentPlayer = Color.RED;
        }
        System.out.println("Current turn: " + currentPlayer);
    }

    public void printBoard() {
        for (int row = ROWS - 1; row >= 0; --row) {
            StringJoiner stringJoiner = new StringJoiner(DELIMITER, DELIMITER, DELIMITER);
            for (int col = 0; col < COLUMNS; ++col) {
                stringJoiner.add(board[col][row].toString());
            }
            System.out.println(stringJoiner.toString());
        }
    }

    public boolean isFinished() {
        int numOfDiscs = 0;
        for (int col = 0; col < COLUMNS; ++col) {
            numOfDiscs += getNumberOfDiscsInColumn(col);
        }
        if (numOfDiscs >= COLUMNS * ROWS) {
            System.out.println("It's a draw");
            return true;
        }
        return false;
    }

    private void checkWinCondition(int col, int row) {
        Pattern winPattern = Pattern.compile(".*" + currentPlayer + "{" + DISCS_FOR_WIN + "}.*");

        //检查垂直方向
        StringJoiner stringJoiner = new StringJoiner("");
        for (int auxRow = 0; auxRow < ROWS; ++auxRow) {
            stringJoiner.add(board[col][auxRow].toString());
        }
        if (winPattern.matcher(stringJoiner.toString()).matches()) {
            winner = currentPlayer;
            System.out.println(currentPlayer + " wins");
        }

        //检查水平方向
        stringJoiner = new StringJoiner("");
        for (int column = 0; column < COLUMNS; ++column) {
            stringJoiner.add(board[column][row].toString());
        }
        if (winPattern.matcher(stringJoiner.toString()).matches()) {
            winner = currentPlayer;
            System.out.println(currentPlayer + " wins");
        }

        // 检查对角线方向
        int startOffset = Math.min(col, row);
        int column = col - startOffset,
                auxRow = row - startOffset;
        stringJoiner = new StringJoiner("");
        do {
            stringJoiner
                    .add(board[column++][auxRow++].toString());
        } while (column < COLUMNS && auxRow < ROWS);
        if (winPattern.matcher(stringJoiner.toString())
                .matches()) {
            winner = currentPlayer;
            System.out.println(currentPlayer +
                    " wins");
            return;
        }
        startOffset = Math.min(col, ROWS - 1 - row);
        column = col - startOffset;
        auxRow = row + startOffset;
        stringJoiner = new StringJoiner("");
        do {
            stringJoiner
                    .add(board[column++][auxRow--].toString());
        } while (column < COLUMNS && auxRow >= 0);
        if (winPattern.matcher(stringJoiner.toString())
                .matches()) {
            winner = currentPlayer;
            System.out.println(currentPlayer +
                    " wins");
        }
    }

    public static void main(String[] args) {
        Connect4 game = new Connect4();
        Scanner scanner = new Scanner(System.in);
        while (!game.isFinished()) {
            System.out.println("Where do the next disc should be placed?");
            game.putDisc(scanner.nextInt());
        }
    }
}
