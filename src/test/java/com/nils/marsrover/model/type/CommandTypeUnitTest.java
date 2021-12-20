package com.nils.marsrover.model.type;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
public class CommandTypeUnitTest {

    @Test
    public void test_get_command_by_key_null() {
        CommandType command = CommandType.getCommandByKey('X');
        Assert.assertTrue("Unexpected command type value for null case", command == null);
    }

    @Test
    public void test_get_command_by_key_success() {
        CommandType command = CommandType.getCommandByKey('R');
        Assert.assertTrue("Unexpected command type value for null case", CommandType.RIGHT.equals(command));
    }
}
