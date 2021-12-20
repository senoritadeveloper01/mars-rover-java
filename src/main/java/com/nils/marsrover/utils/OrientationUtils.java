package com.nils.marsrover.utils;

import com.nils.marsrover.model.type.OrientationType;

/**
 * Util class handling orientation related functions
 * @author  Nil Seri
 */
public class OrientationUtils {

    public static OrientationType moveLeft(OrientationType orientation) {
        switch (orientation) {
            case NORTH:
                return OrientationType.WEST;
            case WEST:
                return OrientationType.SOUTH;
            case EAST:
                return OrientationType.NORTH;
            case SOUTH:
                return OrientationType.EAST;
            default:
                return null;
        }
    }

    public static OrientationType moveRight(OrientationType orientation) {
        switch (orientation) {
            case NORTH:
                return OrientationType.EAST;
            case WEST:
                return OrientationType.NORTH;
            case EAST:
                return OrientationType.SOUTH;
            case SOUTH:
                return OrientationType.WEST;
            default:
                return null;
        }
    }
}
