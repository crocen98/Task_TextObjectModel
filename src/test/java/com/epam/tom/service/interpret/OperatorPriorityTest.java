package com.epam.tom.service.interpret;

import com.epam.tom.exception.NotSupportedOperatorException;
import org.junit.Test;


public class OperatorPriorityTest {
    private static final OperatorPriority priority = OperatorPriority.getInstance();

    @Test(expected = NotSupportedOperatorException.class)
    public void shouldGetNotSupportedOperatorAndThrowException() throws NotSupportedOperatorException {
        priority.priority("*");
    }

}