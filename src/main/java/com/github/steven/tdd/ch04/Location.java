package com.github.steven.tdd.ch04;

/**
 * Location.
 *
 * @author shidingfeng
 */
public class Location {
    private Point point;
    private Direction direction;

    public Location(Point point, Direction direction) {
        this.point = point;
        this.direction = direction;
    }
}
