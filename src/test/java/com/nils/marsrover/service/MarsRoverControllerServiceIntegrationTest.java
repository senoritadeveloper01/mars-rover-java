package com.nils.marsrover.service;

import com.nils.marsrover.model.RoverRobot;
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
public class MarsRoverControllerServiceIntegrationTest {

    private MarsRoverControllerService marsRoverControllerService;

    @Before
    public void setUp() {
        marsRoverControllerService = new MarsRoverControllerService(new InputValidationService(), new PlateauService(), new RoverRobotService());
    }

    @Test
    public void test_solve() {
        RoverRobot roverRobot = marsRoverControllerService.solve(5, 5, 1, 2, 'N', "LMLMLMLMM");
        Assert.assertTrue("Unexpected X coordinate", roverRobot.getCoordinate().getX() == 1);
        Assert.assertTrue("Unexpected Y coordinate", roverRobot.getCoordinate().getY() == 3);
        Assert.assertTrue("Unexpected orientation", OrientationType.NORTH.equals(roverRobot.getOrientation()));

        roverRobot = marsRoverControllerService.solve(5, 5, 3, 3, 'E', "MMRMMRMRRM");
        Assert.assertTrue("Unexpected X coordinate", roverRobot.getCoordinate().getX() == 5);
        Assert.assertTrue("Unexpected Y coordinate", roverRobot.getCoordinate().getY() == 1);
        Assert.assertTrue("Unexpected orientation", OrientationType.EAST.equals(roverRobot.getOrientation()));
    }
}
