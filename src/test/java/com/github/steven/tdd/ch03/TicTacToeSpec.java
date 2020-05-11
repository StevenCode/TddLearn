package com.github.steven.tdd.ch03;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * TicTacToeSpec.
 *
 * @author shidingfeng
 */
public class TicTacToeSpec {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    private TicTacToe ticTacToe;

    @Before
    public final void before() {
        ticTacToe = new TicTacToe();
    }

    @Test
    public void whenXoutSideBoardThenRuntimeException() {
        exception.expect(RuntimeException.class);
        ticTacToe.play(5, 2);
    }

    @Test
    public void whenXoutSideBoardThenRuntimeException1() {
        exception.expect(RuntimeException.class);
        ticTacToe.play(0, 2);
    }

    @Test
    public void whenYoutSideBoardThenRuntimeException() {
        exception.expect(RuntimeException.class);
        ticTacToe.play(2, 5);
    }

    @Test
    public void whenYoutSideBoardThenRuntimeException1() {
        exception.expect(RuntimeException.class);
        ticTacToe.play(2, 0);
    }


    @Test
    public void isInSide() {
        ticTacToe.play(2, 2);
    }

    @Test
    public void whenOccupiedThenRuntimeException() {
        ticTacToe.play(2, 1);
        exception.expect(RuntimeException.class);
        ticTacToe.play(2, 1);
    }
}
