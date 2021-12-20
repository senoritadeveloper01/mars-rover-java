package com.nils.marsrover.model.type;

import java.util.Arrays;
import java.util.Optional;

/**
 * Enum class for orientation (North, South, East, West)
 * @author  Nil Seri
 */
public enum OrientationType {

    NORTH('N'),
    SOUTH('S'),
    EAST('E'),
    WEST('W');

    private final Character key;

    OrientationType(Character key) {
        this.key = key;
    }

    public static OrientationType getOrientationByKey(Character key){
        Optional<OrientationType> typeOptional = Arrays.stream(OrientationType.values()).filter(x -> x.key.equals(key)).findFirst();
        return typeOptional.isPresent() ? typeOptional.get() : null;
    }
}
