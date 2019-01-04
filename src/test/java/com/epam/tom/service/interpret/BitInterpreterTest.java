package com.epam.tom.service.interpret;


import com.epam.tom.exception.NotSupportedOperatorException;
import org.junit.Assert;
import org.junit.Test;

public class BitInterpreterTest {
    private static final Interpreter interpreter = new BitInterpreter();


    @Test
    public void gettingExpressionsAndCalculateValuesEqualsThisExpressions() throws NotSupportedOperatorException {

        int firstValue = interpreter.calculate("(8^5|1&2<<(2|5>>2&71))|1200");
        int secondValue = interpreter.calculate("6&9|(3&4)");
        int ThirdValue = interpreter.calculate("(71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78");

        Assert.assertEquals((8 ^ 5 | 1 & 2 << (2 | 5 >> 2 & 71)) | 1200, firstValue);
        Assert.assertEquals(6 & 9 | (3 & 4), secondValue);
        Assert.assertEquals((71 & (2 & 3 | (3 | (2 & 1 >> 2 | 2) & 2) | 10 & 2)) | 78, ThirdValue);

    }

}