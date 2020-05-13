package com.github.steven.tdd.ch04;

import org.testng.annotations.*;
import static org.testng.Assert.*;

/**
 * ShipSpec.
 *
 * @author shidingfeng
 */
@Test
public class ShipSpec {

    private Ship ship;
    private Location location;

    @BeforeMethod
    public void beforeTest() {
        location = new Location(new Point(21, 13), Direction.NORTH);
        ship = new Ship(location);
    }

    public void givenNorthWhenMoveForwardThenYDecreases() {
        ship.moveForward();
        assertEquals(ship.getLocation().getPoint().getY(),12);
    }

    public void givenEastWhenMoveForwardThenXIncrease() {
        ship.getLocation().setDirection(Direction.EAST);
        ship.moveForward();
        assertEquals(ship.getLocation().getPoint().getX(),22);
    }
}
