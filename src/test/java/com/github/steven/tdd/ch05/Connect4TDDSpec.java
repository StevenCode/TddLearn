package com.github.steven.tdd.ch05;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Connect4TDDSpec.
 *
 * @author shidingfeng
 */
public class Connect4TDDSpec {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Connect4TDD tested;

    @Before
    public void beforeEachTest() {
        tested = new Connect4TDD();
    }

    @Test
    public void whenTheGameIsStartedTheBoardIsEmpty() {
        assertThat(tested.getNumberOfDiscs(), is(0));
    }

    @Test
    public void whenDiscOutsideBoardThenRuntimeException() {
        int column = -1;
        exception.expect(RuntimeException.class);
        exception.expectMessage("Invalid column " + column);
        tested.putDiscInColumn(column);
    }

    @Test
    public void whenFirstDiscInsertedInColumnThenPositionIsZero() {
        int column = 1;
        assertThat(tested.putDiscInColumn(column), is(0));
    }

    @Test
    public void whenSecondDiscInsertedInColumnThenPositionIsOne() {
        int column = 1;
        tested.putDiscInColumn(column);
        assertThat(tested.putDiscInColumn(column), is(1));
    }

    @Test
    public void whenDiscInsertedThenNumberIfDiscsIncrease() {
        int column = 1;
        tested.putDiscInColumn(column);
        assertThat(tested.getNumberOfDiscs(), is(1));
    }

    @Test
    public void whenNoMoreRoomColumnThenRuntimeException() {
        int column = 1;
        int maxDiscsInColumn = 6; // the number of rows
        for (int times = 0;
             times < maxDiscsInColumn;
             ++times) {
            tested.putDiscInColumn(column);
        }
        exception.expect(RuntimeException.class);
        exception
                .expectMessage("No more room in column " +
                        column);
        tested.putDiscInColumn(column);
    }

    @Test
    public void whenFirstPlayerPlaysThenDiscColorIsRed() {
        assertThat(tested.getCurrentPlayer(), is("R"));
    }

    @Test
    public void WhenSecondPlayerPlaysThenDiscColorIsGreen() {
        int column = 1;
        tested.putDiscInColumn(column);
        assertThat(tested.getCurrentPlayer(), is("G"));
    }
}
