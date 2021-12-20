package com.nils.marsrover.model;

import com.nils.marsrover.model.type.OrientationType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
public class RoverRobotUnitTest {

    private RoverRobot roverRobot;

    @Before
    public void setUp() {
        roverRobot = new RoverRobot(new Coordinate(2, 3), OrientationType.NORTH);
    }

    @Test
    public void test_is_on_coordinate_true() {
        Coordinate coordinate = new Coordinate(2, 3);
        boolean result = roverRobot.isOnCoordinate(coordinate);
        Assert.assertTrue("Unexpected result for isOnCoordinate for value true", result == true);
    }

    @Test
    public void test_is_on_coordinate_false() {
        Coordinate coordinate = new Coordinate(2, 4);
        boolean result = roverRobot.isOnCoordinate(coordinate);
        Assert.assertTrue("Unexpected result for isOnCoordinate for value false", result == false);
    }
}
