package com.nils.marsrover.service;

import com.nils.marsrover.exception.MarsRoverException;
import com.nils.marsrover.model.type.CommandType;
import com.nils.marsrover.model.type.OrientationType;
import com.nils.marsrover.model.Plateau;
import com.nils.marsrover.model.Coordinate;
import com.nils.marsrover.model.error.MarsRoverErrorCode;
import com.nils.marsrover.utils.PositionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Request validation class
 * @author  Nil Seri
 */
@Service
@RequiredArgsConstructor
public class InputValidationService {

    public void validatePlateau(Integer dimensionX, Integer dimensionY) {
        if (dimensionX < 0 || dimensionY < 0) {
            throw new MarsRoverException(MarsRoverErrorCode.PLATEAU_COORDINATES_CANNOT_BE_NEGATIVE, dimensionX, dimensionY);
        }
    }

    public void validateInitialState(Integer x, Integer y, Character orientation) {
        validateCoordinates(x, y);
        validateOrientation(orientation);
    }

    private void validateCoordinates(Integer x, Integer y) {
        if (x < 0 || y < 0) {
            throw new MarsRoverException(MarsRoverErrorCode.INITIAL_COORDINATES_CANNOT_BE_NEGATIVE, x, y);
        }
    }

    private void validateOrientation(Character orientation) {
        if (OrientationType.getOrientationByKey(orientation) == null) {
            throw new MarsRoverException(MarsRoverErrorCode.INVALID_ORIENTATION, orientation);
        }
    }

    public void validateCommands(Character[] commands) {
        Arrays.stream(commands).forEach(command -> {
            if( CommandType.getCommandByKey(command) == null) {
                throw new MarsRoverException(MarsRoverErrorCode.INVALID_COMMAND, command);
            }
        });
    }

    /**
     * Check if previously released rover robots block the moving one
     */
    public void validateIfCoordinateIsUnoccupied(Plateau plateau, Coordinate coordinate) {
        if (PositionUtils.isOccupied(plateau, coordinate)) {
            throw new MarsRoverException(MarsRoverErrorCode.POSITION_IS_OCCUPIED, coordinate.getX(), coordinate.getY());
        }
    }
}
