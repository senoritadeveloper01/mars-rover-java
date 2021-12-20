package com.nils.marsrover.utils;

import com.nils.marsrover.model.Plateau;
import com.nils.marsrover.model.Coordinate;
import com.nils.marsrover.model.RoverRobot;

/**
 * Util class handling position related functions
 * @author  Nil Seri
 */
public class PositionUtils {

    public static boolean isOccupied(Plateau plateau, Coordinate coordinate) {
        for (RoverRobot roverRobot : plateau.getRoverRobots()) {
            if (roverRobot.isOnCoordinate(coordinate)) {
                return true;
            }
        }
        return false;
    }
}
