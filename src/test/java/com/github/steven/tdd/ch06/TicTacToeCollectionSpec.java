package com.github.steven.tdd.ch06;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * TicTacToeSpec.
 *
 * @author shidingfeng
 */
public class TicTacToeCollectionSpec {
    TicTacToeCollection collection;
    @Before
    public void before() throws UnknownHostException {
        collection = new TicTacToeCollection();
    }
    @Test
    public void
    whenInstantiatedThenMongoHasDbNameTicTacToe() {
        assertEquals("tic-tac-toe",
                collection.getMongoCollection()
                        .getDBCollection().getDB()
                        .getName());
    }
    @Test
    public void whenInstantiatedThenMongoHasNameGame() {
        assertEquals("game",
                collection.getMongoCollection()
                        .getName());
    }
}
