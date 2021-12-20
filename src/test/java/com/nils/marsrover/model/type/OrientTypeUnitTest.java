package com.nils.marsrover.model.type;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
public class OrientTypeUnitTest {

    @Test
    public void test_get_orientation_by_key_null() {
        OrientationType orientation = OrientationType.getOrientationByKey('X');
        Assert.assertTrue("Unexpected orientation type value for null case", orientation == null);
    }

    @Test
    public void test_get_orientation_by_key_success() {
        OrientationType orientation = OrientationType.getOrientationByKey('S');
        Assert.assertTrue("Unexpected orientation type value for null case", OrientationType.SOUTH.equals(orientation));
    }
}
