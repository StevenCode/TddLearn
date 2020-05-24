package com.github.steven.tdd.ch06;

import java.util.Objects;

/**
 * TicTacToeBean.
 *
 * @author shidingfeng
 */
public class TicTacToeBean {
    private int x;
    private int y;
    private char player;
    private int i;
    public TicTacToeBean(int i, int x, int y, char player) {
        this.i = i;
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicTacToeBean that = (TicTacToeBean) o;
        return x == that.x &&
                y == that.y &&
                player == that.player &&
                i == that.i;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, player, i);
    }
}
