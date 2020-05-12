package com.github.steven.tdd.ch04;

/**
 * Ship.
 *
 * @author shidingfeng
 */
public class Ship {
    private Location location;

    public Ship(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
