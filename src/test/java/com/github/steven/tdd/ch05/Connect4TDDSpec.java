package com.github.steven.tdd.ch05;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Connect4TDDSpec.
 *
 * @author shidingfeng
 */
public class Connect4TDDSpec {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Connect4TDD tested;

    private OutputStream output;

    @Before
    public void beforeEachTest() {
        output = new ByteArrayOutputStream();
        tested = new Connect4TDD(new PrintStream(output));
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

    @Test
    public void whenAskedForCurrentPlayerThenOutputNotice() {
        tested.getCurrentPlayer();
        assertThat(output.toString(), containsString("Player R turn"));
    }

    @Test
    public void whenADiscIsIntroducedTheBoardIsPrinted() {
        int column = 1;
        tested.putDiscInColumn(column);
        assertThat(output.toString(), containsString("| |R| | | | | |"));
    }

    @Test
    public void whenTheGameStartsIsNotFinished() {
        assertFalse("The game must not be finished",
                tested.isFinished());
    }

    @Test
    public void
    whenNoDiscCanBeIntroducedTheGamesIsFinished() {
        for (int row = 0; row < 6; row++)
            for (int column = 0; column < 7; column++)
                tested.putDiscInColumn(column);
        assertTrue("The game must be finished",
                tested.isFinished());
    }
}
