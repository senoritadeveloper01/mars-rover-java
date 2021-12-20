package com.nils.marsrover.model;

import com.nils.marsrover.model.type.OrientationType;
import lombok.*;

/**
 * Keeps rover robot information, coordinates (x,y) and orientation
 * @author  Nil Seri
 */
@Getter
@Setter
@AllArgsConstructor
public class RoverRobot {

    private Coordinate coordinate;

    private OrientationType orientation;

    /**
     * Checks if rover robot exists on the given coordinate
     */
    public boolean isOnCoordinate(Coordinate targetCoordinate) {
        return this.coordinate.isEqual(targetCoordinate);
    }
}
