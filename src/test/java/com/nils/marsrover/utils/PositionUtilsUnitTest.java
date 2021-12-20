package com.nils.marsrover.utils;

import com.nils.marsrover.model.Coordinate;
import com.nils.marsrover.model.Plateau;
import com.nils.marsrover.model.RoverRobot;
import com.nils.marsrover.model.type.OrientationType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
public class PositionUtilsUnitTest {

    private RoverRobot roverRobot1, roverRobot2;

    @Before
    public void setUp() {
        roverRobot1 = new RoverRobot(new Coordinate(2, 3), OrientationType.NORTH);
        roverRobot2 = new RoverRobot(new Coordinate(5, 6), OrientationType.NORTH);
    }

    @Test
    public void test_is_occupied_true_first_value() {
        Plateau plateau = new Plateau(new Coordinate(8, 8), Arrays.asList(roverRobot1, roverRobot2));
        boolean result = PositionUtils.isOccupied(plateau, new Coordinate(2, 3));
        Assert.assertTrue("Unexpected isOccupied return value for first rover robot that occupies actually", result == true);
    }

    @Test
    public void test_is_occupied_true_second_value() {
        Plateau plateau = new Plateau(new Coordinate(8, 8), Arrays.asList(roverRobot1, roverRobot2));
        boolean result = PositionUtils.isOccupied(plateau, new Coordinate(5, 6));
        Assert.assertTrue("Unexpected isOccupied return value for second rover robot that occupies actually", result == true);
    }

    @Test
    public void test_is_occupied_false() {
        Plateau plateau = new Plateau(new Coordinate(8, 8), Arrays.asList(roverRobot1, roverRobot2));
        boolean result = PositionUtils.isOccupied(plateau, new Coordinate(4, 3));
        Assert.assertTrue("Unexpected isOccupied return value for non-occupied coordinates", result == false);
    }
}
