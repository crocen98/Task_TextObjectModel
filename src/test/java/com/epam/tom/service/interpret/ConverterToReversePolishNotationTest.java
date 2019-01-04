package com.epam.tom.service.interpret;


import com.epam.tom.exception.NotSupportedOperatorException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ConverterToReversePolishNotationTest {
    private static final ExpressionConverter converter = ConverterToReversePolishNotation.getInstance();

    @Test
    public void converterGettingStringAndReturnExpressionInPolishForm() throws NotSupportedOperatorException {
        List<String> ob = converter.convert("(8^5|1&2<<(2|5>>2&71))|1200");
        List<String> expected = Arrays.asList("8", "5", "^", "1", "2", "2", "5", "2", ">>", "71", "&", "|", "<<", "&", "|", "1200", "|");
        Assert.assertEquals(ob.size(), expected.size());
        Assert.assertEquals(ob, expected);
    }

}