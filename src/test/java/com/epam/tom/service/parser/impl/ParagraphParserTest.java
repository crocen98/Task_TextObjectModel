package com.epam.tom.service.parser.impl;

import com.epam.tom.entity.TextComponent;
import com.epam.tom.entity.impl.Paragraph;
import com.epam.tom.exception.IncorrectNestingException;
import com.epam.tom.exception.NotSupportedOperatorException;
import com.epam.tom.service.TOMBuilder;
import org.junit.Assert;
import org.junit.Test;


public class ParagraphParserTest {
    private static final ParagraphParser parser = new ParagraphParser();

    @Test
    public void shouldGetTestStringAndCreateTextNodeEqualsEmptyText() throws IncorrectNestingException, NotSupportedOperatorException {
        String example = "Text 344 3435 fdst. we er.";
        TextComponent component = parser.parse(new TOMBuilder(), example);
        Paragraph textExample = new Paragraph();
        Assert.assertEquals(textExample, component);
    }


    @Test
    public void shouldGetNullTestStringAndReturnNull() throws IncorrectNestingException, NotSupportedOperatorException {
        TextComponent component = parser.parse(new TOMBuilder(), null);
        Assert.assertNull(component);
    }

}