package com.nils.marsrover.service;

import com.nils.marsrover.model.Plateau;
import com.nils.marsrover.model.Coordinate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Class handling plateau related functions
 * @author  Nil Seri
 */
@Service
public class PlateauService {

    public Plateau create(Coordinate dimension) {
        return new Plateau(dimension, new ArrayList<>());
    }
}
