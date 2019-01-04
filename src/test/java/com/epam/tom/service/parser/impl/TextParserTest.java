package com.epam.tom.service.parser.impl;

import com.epam.tom.entity.TextComponent;
import com.epam.tom.entity.impl.Text;
import com.epam.tom.exception.IncorrectNestingException;
import com.epam.tom.exception.NotSupportedOperatorException;
import com.epam.tom.service.TOMBuilder;
import org.junit.Assert;
import org.junit.Test;

public class TextParserTest {
    private static final TextParser parser = new TextParser();

    @Test
    public void shouldGetTestStringAndCreateTextNodeEqualsEmptyText() throws IncorrectNestingException, NotSupportedOperatorException {
        String example = "Text 344 3435 fdst. we er.";
        TextComponent component = parser.parse(new TOMBuilder(), example);
        Text textExample = new Text();
        Assert.assertEquals(textExample, component);
    }


    @Test
    public void shouldGetNullTestStringAndReturnNull() throws IncorrectNestingException, NotSupportedOperatorException {
        TextComponent component = parser.parse(new TOMBuilder(), null);
        Assert.assertNull(component);
    }
}