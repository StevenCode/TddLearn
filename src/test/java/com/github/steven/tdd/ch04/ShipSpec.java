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

    public void whenInstantiatedThenLocationIsSet() {
        Location location = new Location(new Point(21, 13), Direction.NORTH);
        Ship ship = new Ship(location);
        assertEquals(ship.getLocation(), location);
    }
}
