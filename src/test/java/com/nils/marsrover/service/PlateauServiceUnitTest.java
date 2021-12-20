package com.nils.marsrover.service;

import com.nils.marsrover.model.Coordinate;
import com.nils.marsrover.model.Plateau;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
public class PlateauServiceUnitTest {

    @InjectMocks
    private PlateauService plateauService;

    @Test
    public void test_plateau_init() {
        Coordinate dimension = new Coordinate(3, 5);
        Plateau plateau = plateauService.create(dimension);

        Assert.assertTrue("Unexpected plateau dimension X value", plateau.getDimension().getX() == 3);
        Assert.assertTrue("Unexpected plateau dimension Y value", plateau.getDimension().getY() == 5);
        Assert.assertTrue("Unexpected rover robots list object", plateau.getRoverRobots() != null);
        Assert.assertTrue("Unexpected rover robots list size", plateau.getRoverRobots().size() == 0);
    }

}
