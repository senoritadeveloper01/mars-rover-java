package com.nils.marsrover.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Keeps plateau information, coordinates (x,y) and existing non-moving roverRobots
 * @author  Nil Seri
 */
@Getter
@Setter
@AllArgsConstructor
public class Plateau {

    private Coordinate dimension;

    private List<RoverRobot> roverRobots;

}
