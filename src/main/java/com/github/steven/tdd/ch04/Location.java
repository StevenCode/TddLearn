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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public boolean forward() {
        if (Direction.EAST.equals(direction)) {
            int x = point.getX();
            point.setX(x + 1);
        }
        int y = point.getY();
        point.setY(y - 1);
        return true;
    }
}
