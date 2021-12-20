package com.nils.marsrover.service;

import com.nils.marsrover.model.Coordinate;
import com.nils.marsrover.model.Plateau;
import com.nils.marsrover.model.RoverRobot;
import com.nils.marsrover.model.type.CommandType;
import com.nils.marsrover.model.type.OrientationType;
import com.nils.marsrover.utils.OrientationUtils;
import com.nils.marsrover.utils.PositionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Class handling rover robot related functions
 * @author  Nil Seri
 */
@Service
@RequiredArgsConstructor
public class RoverRobotService {

    public RoverRobot initializeRoverRobot(Coordinate coordinate, OrientationType orientation) {
        return new RoverRobot(coordinate, orientation);
    }

    public RoverRobot takeAction(Plateau plateau, RoverRobot roverRobot, CommandType command) {
        if (CommandType.MOVE.equals(command)) {
            // if the rover robot is not able to move, do nothing
            if (isAbleToMove(roverRobot, plateau)) {
                return move(roverRobot);
            }
        } else {
            return rotate(roverRobot, command);
        }
        return roverRobot;
    }

    private boolean isAbleToMove(RoverRobot roverRobot, Plateau plateau) {
        // also check if the previous rover is on the spot as well as the plateau edges
        switch (roverRobot.getOrientation()) {
            case NORTH:
                return isAbleToMoveNorth(roverRobot, plateau) && !PositionUtils.isOccupied(plateau,
                        new Coordinate(roverRobot.getCoordinate().getX(), roverRobot.getCoordinate().getY() + 1));
            case SOUTH:
                return isAbleToMoveSouth(roverRobot) && !PositionUtils.isOccupied(plateau,
                        new Coordinate(roverRobot.getCoordinate().getX(), roverRobot.getCoordinate().getY() - 1));
            case EAST:
                return isAbleToMoveEast(roverRobot, plateau) && !PositionUtils.isOccupied(plateau,
                        new Coordinate(roverRobot.getCoordinate().getX() + 1, roverRobot.getCoordinate().getY()));
            case WEST:
                return isAbleToMoveWest(roverRobot) && !PositionUtils.isOccupied(plateau,
                        new Coordinate(roverRobot.getCoordinate().getX() - 1, roverRobot.getCoordinate().getY()));
            default:
                return false;
        }
    }

    private boolean isAbleToMoveNorth(RoverRobot roverRobot, Plateau plateau) {
        return !(roverRobot.getCoordinate().getY() + 1 > plateau.getDimension().getY());
    }

    private boolean isAbleToMoveSouth(RoverRobot roverRobot) {
        return !(roverRobot.getCoordinate().getY() - 1 < 0);
    }

    private boolean isAbleToMoveEast(RoverRobot roverRobot, Plateau plateau) {
        return !(roverRobot.getCoordinate().getX() + 1 > plateau.getDimension().getX());
    }

    private boolean isAbleToMoveWest(RoverRobot roverRobot) {
        return !(roverRobot.getCoordinate().getX() - 1 < 0);
    }

    private RoverRobot move(RoverRobot roverRobot) {
        Coordinate currentCoordinate = roverRobot.getCoordinate();
        switch (roverRobot.getOrientation()) {
            case NORTH:
                roverRobot.getCoordinate().setY(currentCoordinate.getY() + 1);
                break;
            case SOUTH:
                roverRobot.getCoordinate().setY(currentCoordinate.getY() - 1);
                break;
            case EAST:
                roverRobot.getCoordinate().setX(currentCoordinate.getX() + 1);
                break;
            case WEST:
                roverRobot.getCoordinate().setX(currentCoordinate.getX() - 1);
                break;
            default:
                break;
        }
        return roverRobot;
    }

    private RoverRobot rotate(RoverRobot roverRobot, CommandType command) {
        switch (command) {
            case LEFT:
                roverRobot.setOrientation(OrientationUtils.moveLeft(roverRobot.getOrientation()));
                break;
            case RIGHT:
                roverRobot.setOrientation(OrientationUtils.moveRight(roverRobot.getOrientation()));
                break;
            default:
                break;
        }
        return roverRobot;
    }
}
