package com.nils.marsrover.utils;

import com.nils.marsrover.model.type.OrientationType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
public class OrientationUtilsUnitTest {

    @Test
    public void test_move_left_for_north() {
        OrientationType orientation = OrientationType.NORTH;
        OrientationType result = OrientationUtils.moveLeft(orientation);
        Assert.assertTrue("Unexpected moveLeft return value for North", OrientationType.WEST.equals(result));
    }

    @Test
    public void test_move_left_for_west() {
        OrientationType orientation = OrientationType.WEST;
        OrientationType result = OrientationUtils.moveLeft(orientation);
        Assert.assertTrue("Unexpected moveLeft return value for West", OrientationType.SOUTH.equals(result));
    }

    @Test
    public void test_move_left_for_east() {
        OrientationType orientation = OrientationType.EAST;
        OrientationType result = OrientationUtils.moveLeft(orientation);
        Assert.assertTrue("Unexpected moveLeft return value for East", OrientationType.NORTH.equals(result));
    }

    @Test
    public void test_move_left_for_south() {
        OrientationType orientation = OrientationType.SOUTH;
        OrientationType result = OrientationUtils.moveLeft(orientation);
        Assert.assertTrue("Unexpected moveLeft return value for South", OrientationType.EAST.equals(result));
    }

    @Test
    public void test_move_right_for_north() {
        OrientationType orientation = OrientationType.NORTH;
        OrientationType result = OrientationUtils.moveRight(orientation);
        Assert.assertTrue("Unexpected moveRight return value for North", OrientationType.EAST.equals(result));
    }

    @Test
    public void test_move_right_for_west() {
        OrientationType orientation = OrientationType.WEST;
        OrientationType result = OrientationUtils.moveRight(orientation);
        Assert.assertTrue("Unexpected moveRight return value for West", OrientationType.NORTH.equals(result));
    }

    @Test
    public void test_move_right_for_east() {
        OrientationType orientation = OrientationType.EAST;
        OrientationType result = OrientationUtils.moveRight(orientation);
        Assert.assertTrue("Unexpected moveRight return value for East", OrientationType.SOUTH.equals(result));
    }

    @Test
    public void test_move_right_for_south() {
        OrientationType orientation = OrientationType.SOUTH;
        OrientationType result = OrientationUtils.moveRight(orientation);
        Assert.assertTrue("Unexpected moveRight return value for South", OrientationType.WEST.equals(result));
    }
}
