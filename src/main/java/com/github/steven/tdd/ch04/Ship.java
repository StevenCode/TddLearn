package com.github.steven.tdd.ch04;

/**
 * Ship.
 *
 * @author shidingfeng
 */
public class Ship {
    private final Location location;

    public Ship(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public boolean moveForward() {
        location.forward();
        return true;
    }
}
