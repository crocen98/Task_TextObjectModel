package com.epam.tom.service.parser.impl;

import com.epam.tom.entity.impl.Lexeme;
import com.epam.tom.entity.impl.Symbol;
import com.epam.tom.entity.impl.TextPart;
import com.epam.tom.entity.impl.Word;
import com.epam.tom.exception.IncorrectNestingException;
import com.epam.tom.exception.NotSupportedOperatorException;
import com.epam.tom.service.TOMBuilder;
import org.junit.Assert;
import org.junit.Test;


public class LeafParserTest {
    private static final LeafParser parser = new LeafParser();

    @Test
    public void gettingStringMatchesWordAndCreateWordEqualsTestWord() throws IncorrectNestingException, NotSupportedOperatorException {
        String testString = "A234";
        String secondTestString = "asa-asd";

        TOMBuilder firstBuilder = new TOMBuilder();
        TOMBuilder secondBuilder = new TOMBuilder();

        TextPart firstLexeme = new Lexeme();
        firstLexeme.append(new Word(testString));
        TextPart secondLexeme = new Lexeme();
        secondLexeme.append(new Word(secondTestString));

        firstBuilder.append(new Lexeme());
        secondBuilder.append(new Lexeme());
        parser.parse(firstBuilder, testString);
        parser.parse(secondBuilder, secondTestString);

        Assert.assertEquals(firstLexeme, firstBuilder.getComponent());
        Assert.assertEquals(secondLexeme, secondBuilder.getComponent());
    }

    @Test
    public void gettingStringMatchesSymbolAndCreateSymbolEqualsTestWord() throws IncorrectNestingException, NotSupportedOperatorException {
        String testString = "—";
        String secondTestString = "(";

        TOMBuilder firstBuilder = new TOMBuilder();
        TOMBuilder secondBuilder = new TOMBuilder();

        TextPart firstLexeme = new Lexeme();
        firstLexeme.append(new Symbol(testString));
        TextPart secondLexeme = new Lexeme();
        secondLexeme.append(new Symbol(secondTestString));

        firstBuilder.append(new Lexeme());
        secondBuilder.append(new Lexeme());
        parser.parse(firstBuilder, testString);
        parser.parse(secondBuilder, secondTestString);

        Assert.assertEquals(firstLexeme, firstBuilder.getComponent());
        Assert.assertEquals(secondLexeme, secondBuilder.getComponent());
    }


    @Test
    public void gettingStringMatchesBitExpressionAndCreateWordEqualsResultOfExpression() throws IncorrectNestingException, NotSupportedOperatorException {
        String testString = "(71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78";
        String secondTestString = "(13>>2)";


        TOMBuilder firstBuilder = new TOMBuilder();
        TOMBuilder secondBuilder = new TOMBuilder();

        TextPart firstLexeme = new Lexeme();
        firstLexeme.append(new Word(Integer.toString((71 & (2 & 3 | (3 | (2 & 1 >> 2 | 2) & 2) | 10 & 2)) | 78)));
        TextPart secondLexeme = new Lexeme();
        secondLexeme.append(new Word(Integer.toString((13 >> 2))));

        firstBuilder.append(new Lexeme());
        secondBuilder.append(new Lexeme());
        parser.parse(firstBuilder, testString);
        parser.parse(secondBuilder, secondTestString);

        Assert.assertEquals(firstLexeme, firstBuilder.getComponent());
        Assert.assertEquals(secondLexeme, secondBuilder.getComponent());
    }

}