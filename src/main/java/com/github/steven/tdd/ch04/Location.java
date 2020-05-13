package com.github.steven.tdd.ch04;

/**
 * Location.
 *
 * @author shidingfeng
 */
public class Location {
    private Point point;
    private Direction direction;

    private static final int FORWARD = 1;
    private static final int BACKWARD = -1;

    public int getX() {
        return point.getX();
    }

    public int getY() {
        return point.getY();
    }

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

    public Location copy() {
        return new Location(new Point(point.getX(), point.getY()), direction);
    }

    public boolean forward() {
        move(FORWARD);
        return true;
    }

    public boolean backward() {
        move(BACKWARD);
        return true;
    }

    public void turnLeft() {
        this.direction = direction.turnLeft();
    }

    public void turnRight() {
        this.direction = direction.turnRight();
    }

    private boolean move(int fw) {
        int x = getX();
        int y = getY();
        switch (direction) {
            case EAST:
                point.setX(x + fw);
                break;
            case WEST:
                point.setX(x - fw);
                break;
            case NORTH:
                point.setY(y - fw);
                break;
            case SOUTH:
                point.setY(y + fw);
                break;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Location location = (Location) o;
        if (getX() != location.getX()){ return false;}
        if (getY() != location.getY()) {return false;}
        if (direction != location.direction) {return false; }
        return true;
    }
}
