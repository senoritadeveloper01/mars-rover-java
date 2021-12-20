package com.nils.marsrover.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Keeps coordinate information (x,y)
 * @author  Nil Seri
 */
@Getter
@Setter
@AllArgsConstructor
public class Coordinate {

    // TODO: NilS Record'a çevir

    private Integer x;

    private Integer y;

    public boolean isEqual(Coordinate coordinate) {
        return this.x == coordinate.x && this.y == coordinate.y;
    }

    @Override
    public String toString() {
        return this.x + " " + this.y;
    }
}
