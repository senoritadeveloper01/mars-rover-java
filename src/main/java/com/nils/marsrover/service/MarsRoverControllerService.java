package com.nils.marsrover.service;

import com.nils.marsrover.model.Coordinate;
import com.nils.marsrover.model.Plateau;
import com.nils.marsrover.model.RoverRobot;
import com.nils.marsrover.model.type.CommandType;
import com.nils.marsrover.model.type.OrientationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Solver class
 * @author  Nil Seri
 */
@Service
@RequiredArgsConstructor
public class MarsRoverControllerService {

    private final InputValidationService inputValidationService;

    private final PlateauService plateauService;

    private final RoverRobotService roverRobotService;

    public RoverRobot solve(Integer dimensionX, Integer dimensionY, Integer x, Integer y, Character roverOrientation, String commands) {
        inputValidationService.validatePlateau(dimensionX, dimensionY);
        inputValidationService.validateInitialState(x, y, roverOrientation);

        Character[] commandArray = commands.chars().mapToObj(ch -> (char) ch).toArray(Character[]::new);
        inputValidationService.validateCommands(commandArray);

        OrientationType orientation = OrientationType.getOrientationByKey(roverOrientation);
        List<CommandType> commandList = Arrays.stream(commandArray).map(CommandType::getCommandByKey).collect(Collectors.toList());

        Coordinate dimension = new Coordinate(dimensionX, dimensionY);
        Plateau plateau = plateauService.create(dimension);

        Coordinate coordinate = new Coordinate(x, y);
        inputValidationService.validateIfCoordinateIsUnoccupied(plateau, coordinate);

        RoverRobot roverRobot = roverRobotService.initializeRoverRobot(coordinate, orientation);

        roverRobot = fulfillCommands(plateau, roverRobot, commandList);

        // mark final rover robot's position as an obstacle for the next rover robot
        plateau.getRoverRobots().add(roverRobot);

        return roverRobot;
    }

    private RoverRobot fulfillCommands(Plateau plateau, RoverRobot roverRobot, List<CommandType> commands) {
        for (CommandType command : commands) {
            roverRobot = roverRobotService.takeAction(plateau, roverRobot, command);
        }
        return roverRobot;
    }

}
