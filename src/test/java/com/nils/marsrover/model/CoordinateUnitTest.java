package com.nils.marsrover.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
public class CoordinateUnitTest {

    @Test
    public void test_is_equal_false_different_x() {
        Coordinate coordinate1 = new Coordinate(3, 5);
        Coordinate coordinate2 = new Coordinate(5, 5);
        boolean result = coordinate1.isEqual(coordinate2);
        Assert.assertTrue("Unexpected result for isEqual when x values are different", result == false);
    }

    @Test
    public void test_is_equal_false_different_y() {
        Coordinate coordinate1 = new Coordinate(3, 5);
        Coordinate coordinate2 = new Coordinate(3, 6);
        boolean result = coordinate1.isEqual(coordinate2);
        Assert.assertTrue("Unexpected result for isEqual when y values are different", result == false);
    }

    @Test
    public void test_is_equal_false_both_different() {
        Coordinate coordinate1 = new Coordinate(3, 5);
        Coordinate coordinate2 = new Coordinate(5, 6);
        boolean result = coordinate1.isEqual(coordinate2);
        Assert.assertTrue("Unexpected result for isEqual when both values are different", result == false);
    }

    @Test
    public void test_is_equal_true() {
        Coordinate coordinate1 = new Coordinate(5, 5);
        Coordinate coordinate2 = new Coordinate(5, 5);
        boolean result = coordinate1.isEqual(coordinate2);
        Assert.assertTrue("Unexpected result for isEqual when both values are actually equal", result == true);
    }

    @Test
    public void test_to_string() {
        Coordinate coordinate = new Coordinate(3, 5);
        String result = coordinate.toString();
        Assert.assertTrue("Unexpected result for toString", "3 5".equals(result));
    }
}
