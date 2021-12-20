package com.nils.marsrover.service;

import com.nils.marsrover.model.Coordinate;
import com.nils.marsrover.model.Plateau;
import com.nils.marsrover.model.RoverRobot;
import com.nils.marsrover.model.type.CommandType;
import com.nils.marsrover.model.type.OrientationType;
import com.nils.marsrover.utils.OrientationUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.powermock.reflect.Whitebox;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@PrepareForTest({RoverRobotService.class, OrientationUtils.class})
public class RoverRobotServiceUnitTest {

    @Spy
    //@InjectMocks
    private RoverRobotService roverRobotService;

    @Before
    public void setUp() {
        PowerMockito.mockStatic(OrientationUtils.class);
    }

    @Test
    public void test_rover_robot_init() {
        Coordinate dimension = new Coordinate(3, 5);
        OrientationType orientation = OrientationType.SOUTH;
        RoverRobot roverRobot = roverRobotService.initializeRoverRobot(dimension, orientation);

        Assert.assertTrue("Unexpected rover robot coordinate X value", roverRobot.getCoordinate().getX() == 3);
        Assert.assertTrue("Unexpected rover robot coordinate Y value", roverRobot.getCoordinate().getY() == 5);
        Assert.assertTrue("Unexpected rover robot orientation", OrientationType.SOUTH.equals(roverRobot.getOrientation()));
    }

    @Test
    public void test_is_able_to_move_north_false() throws Exception {
        RoverRobot roverRobot = new RoverRobot(new Coordinate(3, 6), OrientationType.SOUTH);
        Plateau plateau = new Plateau(new Coordinate(6, 6), new ArrayList<>());
        boolean isAble = Whitebox.invokeMethod(roverRobotService, "isAbleToMoveNorth", roverRobot, plateau);
        Assert.assertTrue("Unexpected output for invalid move for North", isAble == false);

    }

    @Test
    public void test_is_able_to_move_north_true() throws Exception {
        RoverRobot roverRobot = new RoverRobot(new Coordinate(3, 5), OrientationType.SOUTH);
        Plateau plateau = new Plateau(new Coordinate(6, 6), new ArrayList<>());
        boolean isAble = Whitebox.invokeMethod(roverRobotService, "isAbleToMoveNorth", roverRobot, plateau);
        Assert.assertTrue("Unexpected output for valid move for North", isAble == true);
    }

    @Test
    public void test_is_able_to_move_south_false() throws Exception {
        RoverRobot roverRobot = new RoverRobot(new Coordinate(6, 0), OrientationType.SOUTH);
        boolean isAble = Whitebox.invokeMethod(roverRobotService, "isAbleToMoveSouth", roverRobot);
        Assert.assertTrue("Unexpected output for invalid move for South", isAble == false);

    }

    @Test
    public void test_is_able_to_move_south_true() throws Exception {
        RoverRobot roverRobot = new RoverRobot(new Coordinate(5, 1), OrientationType.SOUTH);
        boolean isAble = Whitebox.invokeMethod(roverRobotService, "isAbleToMoveSouth", roverRobot);
        Assert.assertTrue("Unexpected output for valid move for South", isAble == true);
    }

    @Test
    public void test_is_able_to_move_east_false() throws Exception {
        RoverRobot roverRobot = new RoverRobot(new Coordinate(6, 5), OrientationType.SOUTH);
        Plateau plateau = new Plateau(new Coordinate(6, 6), new ArrayList<>());
        boolean isAble = Whitebox.invokeMethod(roverRobotService, "isAbleToMoveEast", roverRobot, plateau);
        Assert.assertTrue("Unexpected output for invalid move for East", isAble == false);

    }

    @Test
    public void test_is_able_to_move_east_true() throws Exception {
        RoverRobot roverRobot = new RoverRobot(new Coordinate(5, 5), OrientationType.SOUTH);
        Plateau plateau = new Plateau(new Coordinate(6, 6), new ArrayList<>());
        boolean isAble = Whitebox.invokeMethod(roverRobotService, "isAbleToMoveEast", roverRobot, plateau);
        Assert.assertTrue("Unexpected output for valid move for East", isAble == true);
    }

    @Test
    public void test_is_able_to_move_west_false() throws Exception {
        RoverRobot roverRobot = new RoverRobot(new Coordinate(0, 5), OrientationType.SOUTH);
        boolean isAble = Whitebox.invokeMethod(roverRobotService, "isAbleToMoveWest", roverRobot);
        Assert.assertTrue("Unexpected output for invalid move for West", isAble == false);
    }

    @Test
    public void test_is_able_to_move_west_true() throws Exception {
        RoverRobot roverRobot = new RoverRobot(new Coordinate(1, 5), OrientationType.SOUTH);
        boolean isAble = Whitebox.invokeMethod(roverRobotService, "isAbleToMoveWest", roverRobot);
        Assert.assertTrue("Unexpected output for valid move for West", isAble == true);
    }

    @Test
    public void test_is_able_to_move_north_is_called() throws Exception {
        RoverRobot roverRobot = new RoverRobot(new Coordinate(5, 5), OrientationType.NORTH);
        Plateau plateau = new Plateau(new Coordinate(6, 6), new ArrayList<>());
        Whitebox.invokeMethod(roverRobotService, "isAbleToMove", roverRobot, plateau);

        PowerMockito.verifyPrivate(roverRobotService, Mockito.times(1)).
                invoke("isAbleToMoveNorth", Mockito.any(RoverRobot.class), Mockito.any(Plateau.class));
        PowerMockito.verifyPrivate(roverRobotService, Mockito.times(0)).
                invoke("isAbleToMoveSouth", Mockito.any(RoverRobot.class));
        PowerMockito.verifyPrivate(roverRobotService, Mockito.times(0)).
                invoke("isAbleToMoveEast", Mockito.any(RoverRobot.class), Mockito.any(Plateau.class));
        PowerMockito.verifyPrivate(roverRobotService, Mockito.times(0)).
                invoke("isAbleToMoveWest", Mockito.any(RoverRobot.class));
    }

    @Test
    public void test_is_able_to_move_south_is_called() throws Exception {
        RoverRobot roverRobot = new RoverRobot(new Coordinate(5, 5), OrientationType.SOUTH);
        Plateau plateau = new Plateau(new Coordinate(6, 6), new ArrayList<>());
        Whitebox.invokeMethod(roverRobotService, "isAbleToMove", roverRobot, plateau);

        PowerMockito.verifyPrivate(roverRobotService, Mockito.times(0)).
                invoke("isAbleToMoveNorth", Mockito.any(RoverRobot.class), Mockito.any(Plateau.class));
        PowerMockito.verifyPrivate(roverRobotService, Mockito.times(1)).
                invoke("isAbleToMoveSouth", Mockito.any(RoverRobot.class));
        PowerMockito.verifyPrivate(roverRobotService, Mockito.times(0)).
                invoke("isAbleToMoveEast", Mockito.any(RoverRobot.class), Mockito.any(Plateau.class));
        PowerMockito.verifyPrivate(roverRobotService, Mockito.times(0)).
                invoke("isAbleToMoveWest", Mockito.any(RoverRobot.class));
    }

    @Test
    public void test_is_able_to_move_east_is_called() throws Exception {
        RoverRobot roverRobot = new RoverRobot(new Coordinate(5, 5), OrientationType.EAST);
        Plateau plateau = new Plateau(new Coordinate(6, 6), new ArrayList<>());
        Whitebox.invokeMethod(roverRobotService, "isAbleToMove", roverRobot, plateau);

        PowerMockito.verifyPrivate(roverRobotService, Mockito.times(0)).
                invoke("isAbleToMoveNorth", Mockito.any(RoverRobot.class), Mockito.any(Plateau.class));
        PowerMockito.verifyPrivate(roverRobotService, Mockito.times(0)).
                invoke("isAbleToMoveSouth", Mockito.any(RoverRobot.class));
        PowerMockito.verifyPrivate(roverRobotService, Mockito.times(1)).
                invoke("isAbleToMoveEast", Mockito.any(RoverRobot.class), Mockito.any(Plateau.class));
        PowerMockito.verifyPrivate(roverRobotService, Mockito.times(0)).
                invoke("isAbleToMoveWest", Mockito.any(RoverRobot.class));
    }

    @Test
    public void test_is_able_to_move_west_is_called() throws Exception {
        RoverRobot roverRobot = new RoverRobot(new Coordinate(5, 5), OrientationType.WEST);
        Plateau plateau = new Plateau(new Coordinate(6, 6), new ArrayList<>());
        Whitebox.invokeMethod(roverRobotService, "isAbleToMove", roverRobot, plateau);

        PowerMockito.verifyPrivate(roverRobotService, Mockito.times(0)).
                invoke("isAbleToMoveNorth", Mockito.any(RoverRobot.class), Mockito.any(Plateau.class));
        PowerMockito.verifyPrivate(roverRobotService, Mockito.times(0)).
                invoke("isAbleToMoveSouth", Mockito.any(RoverRobot.class));
        PowerMockito.verifyPrivate(roverRobotService, Mockito.times(0)).
                invoke("isAbleToMoveEast", Mockito.any(RoverRobot.class), Mockito.any(Plateau.class));
        PowerMockito.verifyPrivate(roverRobotService, Mockito.times(1)).
                invoke("isAbleToMoveWest", Mockito.any(RoverRobot.class));
    }

    @Test
    public void test_is_able_to_move_north_is_occupied() throws Exception {
        RoverRobot roverRobot = new RoverRobot(new Coordinate(5, 5), OrientationType.NORTH);
        RoverRobot previousRoverRobot = new RoverRobot(new Coordinate(5, 6), OrientationType.EAST);
        Plateau plateau = new Plateau(new Coordinate(6, 6), Arrays.asList(previousRoverRobot));
        boolean isAble = Whitebox.invokeMethod(roverRobotService, "isAbleToMove", roverRobot, plateau);
        Assert.assertTrue("Unexpected output for move North occupied case", isAble == false);
    }

    @Test
    public void test_is_able_to_move_south_is_occupied() throws Exception {
        RoverRobot roverRobot = new RoverRobot(new Coordinate(5, 5), OrientationType.SOUTH);
        RoverRobot previousRoverRobot = new RoverRobot(new Coordinate(5, 4), OrientationType.EAST);
        Plateau plateau = new Plateau(new Coordinate(6, 6), Arrays.asList(previousRoverRobot));
        boolean isAble = Whitebox.invokeMethod(roverRobotService, "isAbleToMove", roverRobot, plateau);
        Assert.assertTrue("Unexpected output for move South occupied case", isAble == false);
    }

    @Test
    public void test_is_able_to_move_east_is_occupied() throws Exception {
        RoverRobot roverRobot = new RoverRobot(new Coordinate(5, 5), OrientationType.EAST);
        RoverRobot previousRoverRobot = new RoverRobot(new Coordinate(6, 5), OrientationType.EAST);
        Plateau plateau = new Plateau(new Coordinate(6, 6), Arrays.asList(previousRoverRobot));
        boolean isAble = Whitebox.invokeMethod(roverRobotService, "isAbleToMove", roverRobot, plateau);
        Assert.assertTrue("Unexpected output for move East occupied case", isAble == false);
    }

    @Test
    public void test_is_able_to_move_west_is_occupied() throws Exception {
        RoverRobot roverRobot = new RoverRobot(new Coordinate(5, 5), OrientationType.WEST);
        RoverRobot previousRoverRobot = new RoverRobot(new Coordinate(4, 5), OrientationType.EAST);
        Plateau plateau = new Plateau(new Coordinate(6, 6), Arrays.asList(previousRoverRobot));
        boolean isAble = Whitebox.invokeMethod(roverRobotService, "isAbleToMove", roverRobot, plateau);
        Assert.assertTrue("Unexpected output for move West occupied case", isAble == false);
    }

    @Test
    public void test_move_north() throws Exception {
        RoverRobot roverRobot = new RoverRobot(new Coordinate(1, 1), OrientationType.NORTH);
        roverRobot = Whitebox.invokeMethod(roverRobotService, "move", roverRobot);
        Assert.assertTrue("Unexpected output for move North",
                roverRobot.getCoordinate().getY() == 2);
    }

    @Test
    public void test_move_south() throws Exception {
        RoverRobot roverRobot = new RoverRobot(new Coordinate(1, 1), OrientationType.SOUTH);
        roverRobot = Whitebox.invokeMethod(roverRobotService, "move", roverRobot);
        Assert.assertTrue("Unexpected output for move South",
                roverRobot.getCoordinate().getY() == 0);
    }

    @Test
    public void test_move_east() throws Exception {
        RoverRobot roverRobot = new RoverRobot(new Coordinate(1, 1), OrientationType.EAST);
        roverRobot = Whitebox.invokeMethod(roverRobotService, "move", roverRobot);
        Assert.assertTrue("Unexpected output for move East", roverRobot.getCoordinate().getX() == 2);
    }

    @Test
    public void test_move_west() throws Exception {
        RoverRobot roverRobot = new RoverRobot(new Coordinate(1, 1), OrientationType.WEST);
        roverRobot = Whitebox.invokeMethod(roverRobotService, "move", roverRobot);
        Assert.assertTrue("Unexpected output for move West", roverRobot.getCoordinate().getX() == 0);
    }

    @Test
    public void test_rotate_left() throws Exception {
        RoverRobot roverRobot = new RoverRobot(new Coordinate(1, 1), OrientationType.WEST);
        CommandType command = CommandType.LEFT;
        Whitebox.invokeMethod(roverRobotService, "rotate", roverRobot, command);

        PowerMockito.verifyStatic(OrientationUtils.class, VerificationModeFactory.times(1));
        OrientationUtils.moveLeft(Mockito.any(OrientationType.class));
    }

    @Test
    public void test_rotate_right() throws Exception {
        RoverRobot roverRobot = new RoverRobot(new Coordinate(1, 1), OrientationType.WEST);
        CommandType command = CommandType.RIGHT;
        Whitebox.invokeMethod(roverRobotService, "rotate", roverRobot, command);

        PowerMockito.verifyStatic(OrientationUtils.class, VerificationModeFactory.times(1));
        OrientationUtils.moveRight(Mockito.any(OrientationType.class));
    }

    @Test
    public void test_take_action_move_nok() throws Exception {
        PowerMockito.doReturn(false).when(roverRobotService, "isAbleToMove",Mockito.any(RoverRobot.class), Mockito.any(Plateau.class));
        CommandType command = CommandType.MOVE;

        Plateau plateau = new Plateau(new Coordinate(3, 3), new ArrayList<>());
        RoverRobot roverRobot = new RoverRobot(new Coordinate(1, 2), OrientationType.EAST);
        roverRobotService.takeAction(plateau, roverRobot, command);

        PowerMockito.verifyPrivate(roverRobotService, Mockito.times(0)).invoke("move", Mockito.any(RoverRobot.class));
        PowerMockito.verifyPrivate(roverRobotService, Mockito.times(0)).
                invoke("rotate", Mockito.any(RoverRobot.class),  Mockito.any(CommandType.class));
    }

    @Test
    public void test_take_action_move_ok() throws Exception {
        PowerMockito.doReturn(true).when(roverRobotService, "isAbleToMove",Mockito.any(RoverRobot.class), Mockito.any(Plateau.class));
        CommandType command = CommandType.MOVE;

        Plateau plateau = new Plateau(new Coordinate(3, 3), new ArrayList<>());
        RoverRobot roverRobot = new RoverRobot(new Coordinate(1, 2), OrientationType.EAST);
        roverRobotService.takeAction(plateau, roverRobot, command);

        PowerMockito.verifyPrivate(roverRobotService, Mockito.times(1)).invoke("move", Mockito.any(RoverRobot.class));
        PowerMockito.verifyPrivate(roverRobotService, Mockito.times(0)).
                invoke("rotate", Mockito.any(RoverRobot.class),  Mockito.any(CommandType.class));
    }

    @Test
    public void test_take_action_rotate_ok() throws Exception {
        PowerMockito.doReturn(true).when(roverRobotService, "isAbleToMove",Mockito.any(RoverRobot.class), Mockito.any(Plateau.class));
        CommandType command = CommandType.RIGHT;

        Plateau plateau = new Plateau(new Coordinate(3, 3), new ArrayList<>());
        RoverRobot roverRobot = new RoverRobot(new Coordinate(1, 2), OrientationType.EAST);
        roverRobotService.takeAction(plateau, roverRobot, command);

        PowerMockito.verifyPrivate(roverRobotService, Mockito.times(0)).invoke("move", Mockito.any(RoverRobot.class));
        PowerMockito.verifyPrivate(roverRobotService, Mockito.times(1)).
                invoke("rotate", Mockito.any(RoverRobot.class),  Mockito.any(CommandType.class));
    }
}
