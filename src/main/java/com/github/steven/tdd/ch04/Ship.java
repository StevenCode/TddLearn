package com.github.steven.tdd.ch04;

/**
 * Ship.
 *
 * @author shidingfeng
 */
public class Ship {
    private final Location location;
    private Planet planet;

    public Ship(Location location, Planet planet) {
        this.location = location;
        this.planet = planet;
    }

    public Planet getPlanet() {
        return planet;
    }

    public Location getLocation() {
        return location;
    }

    public boolean moveForward() {
        return location.forward();
    }

    public boolean moveBackward() {
        return location.backward();
    }

    public void turnRight() {
        location.turnRight();
    }

    public void turnLeft() {
        location.turnLeft();
    }

    public void receiveCommands(String commands) {
        for (char command : commands.toCharArray()) {
            switch (command) {
                case 'f':
                    moveForward();
                    break;
                case 'b':
                    moveBackward();
                    break;
                case 'l':
                    turnLeft();
                    break;
                case 'r':
                    turnRight();
                    break;
            }
        }
    }
}
