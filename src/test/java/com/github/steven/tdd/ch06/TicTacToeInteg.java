package com.github.steven.tdd.ch06;

import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;

public class TicTacToeInteg {

    @Test
    public void givenMongoDbIsRunningWhenPlayThenNoException() throws UnknownHostException {
        TicTacToe ticTacToe = new TicTacToe();
        assertEquals(TicTacToe.NO_WINNER, ticTacToe.play(1, 1));
    }
}
