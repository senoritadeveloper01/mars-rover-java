package com.nils.marsrover.service;

import com.nils.marsrover.exception.MarsRoverException;
import com.nils.marsrover.model.Coordinate;
import com.nils.marsrover.model.Plateau;
import com.nils.marsrover.model.RoverRobot;
import com.nils.marsrover.model.type.OrientationType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
public class InputValidationServiceUnitTest {

    @InjectMocks
    private InputValidationService inputValidationService;

    @Test
    public void test_validate_invalid_plateau_dimension_x_negative() {
        MarsRoverException e = Assert.assertThrows(MarsRoverException.class, () -> inputValidationService.validatePlateau(-5, 8));
        Assert.assertTrue("Unexpected error msg for plateau negative dimension X",
                e.getMessage().contains("Plateau coordinates cannot be negative, x: -5, y: 8"));
    }

    @Test
    public void test_validate_invalid_plateau_dimension_y_negative() {
        MarsRoverException e = Assert.assertThrows(MarsRoverException.class, () -> inputValidationService.validatePlateau(5, -8));
        Assert.assertTrue("Unexpected error msg for plateau negative dimension Y",
                e.getMessage().contains("Plateau coordinates cannot be negative, x: 5, y: -8"));
    }

    @Test
    public void test_validate_valid_plateau_dimensions() {
        inputValidationService.validatePlateau(5, 8);
    }

    @Test
    public void test_validate_invalid_initial_state_coordinate_x_negative() {
        MarsRoverException e = Assert.assertThrows(MarsRoverException.class, () ->
                inputValidationService.validateInitialState(-5, 8, 'N'));
        Assert.assertTrue("Unexpected error msg for rover robot coordinate X",
                e.getMessage().contains("Initial coordinates cannot be negative, x: -5, y: 8"));
    }

    @Test
    public void test_validate_invalid_initial_state_coordinate_y_negative() {
        MarsRoverException e = Assert.assertThrows(MarsRoverException.class, () ->
                inputValidationService.validateInitialState(5, -8, 'N'));
        Assert.assertTrue("Unexpected error msg for rover robot coordinate Y",
                e.getMessage().contains("Initial coordinates cannot be negative, x: 5, y: -8"));
    }

    @Test
    public void test_validate_invalid_orientation() {
        MarsRoverException e = Assert.assertThrows(MarsRoverException.class, () ->
                inputValidationService.validateInitialState(5, 8, 'X'));
        Assert.assertTrue("Unexpected error msg for rover robot orientation",
                e.getMessage().contains("Invalid orientation (possible values include 'N'-'S'-'E'-'W'), orientation: X"));
    }

    @Test
    public void test_validate_valid_initial_state() {
       inputValidationService.validateInitialState(5, 8, 'W');
    }

    @Test
    public void test_validate_invalid_commands() {
        MarsRoverException e = Assert.assertThrows(MarsRoverException.class, () ->
                inputValidationService.validateCommands(new Character[]{'L', 'X', 'R'}));
        Assert.assertTrue("Unexpected error msg for invalid command",
                e.getMessage().contains("Invalid command (possible values include 'L'-'R'-'M'), command : X"));
    }

    @Test
    public void test_validate_valid_commands() {
        inputValidationService.validateCommands(new Character[]{'L', 'M', 'R'});
    }

    @Test
    public void test_validate_invalid_occupied_coordinate() {
        RoverRobot roverRobot = new RoverRobot(new Coordinate(2, 3), OrientationType.WEST);
        Plateau plateau = new Plateau(new Coordinate(3,4), Arrays.asList(roverRobot));
        Coordinate roverRobotCoordinate = new Coordinate(2, 3);

        MarsRoverException e = Assert.assertThrows(MarsRoverException.class, () ->
                inputValidationService.validateIfCoordinateIsUnoccupied(plateau, roverRobotCoordinate));
        Assert.assertTrue("Unexpected error msg for invalid occupied coordinate",
                e.getMessage().contains("Position is occupied by previously moved rover robot: x: 2, y: 3"));
    }

    @Test
    public void test_validate_valid_unoccupied_coordinate() {
        RoverRobot roverRobot = new RoverRobot(new Coordinate(2, 3), OrientationType.WEST);
        Plateau plateau = new Plateau(new Coordinate(3,4), Arrays.asList(roverRobot));
        Coordinate roverRobotCoordinate = new Coordinate(3, 3);

        inputValidationService.validateIfCoordinateIsUnoccupied(plateau, roverRobotCoordinate);
    }
}
