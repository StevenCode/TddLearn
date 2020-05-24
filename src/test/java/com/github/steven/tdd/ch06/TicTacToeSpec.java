package com.github.steven.tdd.ch06;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class TicTacToeSpec {
    TicTacToe ticTacToe;
    TicTacToeCollection collection;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public final void before() {
        collection = mock(TicTacToeCollection.class);
        doReturn(true)
                .when(collection)
                .saveMove(any(TicTacToeBean.class));
        ticTacToe = new TicTacToe(collection);
    }

    @Test
    public void whenInstantiatedTheSetCollection() {
        assertNotNull(ticTacToe.getTicTacToeCollection());
    }


    @Test
    public void whenPlayThenSaveMoveIsInvoked() {
        TicTacToeBean move = new TicTacToeBean(1, 1, 3, 'X');
        ticTacToe.play(move.getX(), move.getY());
        verify(collection, times(1)).saveMove(move);
    }

    @Test
    public void whenPlayAndSaveReturnsFalseThenThrowExcetion() {
        doReturn(false)
                .when(collection)
                .saveMove(any(TicTacToeBean.class));
        TicTacToeBean move = new TicTacToeBean(1, 1, 3, 'X');
        exception.expect(RuntimeException.class);
        ticTacToe.play(move.getX(), move.getY());
    }

    @Test
    public void whenPlayInvokedMultipleTimesThenTurnIncreases() {
        TicTacToeBean move1 = new TicTacToeBean(1, 1, 1, 'X');
        ticTacToe.play(move1.getX(), move1.getY());
        verify(collection, times(1)).saveMove(move1);
        TicTacToeBean move2 = new TicTacToeBean(2, 1, 2, 'O');
        ticTacToe.play(move2.getX(), move2.getY());
        verify(collection, times(1)).saveMove(move2);
    }
}
