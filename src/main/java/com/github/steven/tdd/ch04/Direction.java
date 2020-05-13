package com.github.steven.tdd.ch04;

/**
 * Direction.
 *
 * @author shidingfeng
 */
public enum Direction {
    NORTH(0, 'N'),
    EAST(1, 'E'),
    SOUTH(2, 'S'),
    WEST(3, 'W'),
    NONE(4, 'X');

    private final int value;
    private final char shortName;

    private Direction(final int newValue, final char shortNameValue) {
        value = newValue;
        shortName = shortNameValue;
    }

    public Direction turnLeft() {
        int index = (value + 3) % 4;
        return Direction.values()[index];
    }

    public Direction turnRight() {
        int index = (value + 1) % 4;
        return Direction.values()[index];
    }
}
