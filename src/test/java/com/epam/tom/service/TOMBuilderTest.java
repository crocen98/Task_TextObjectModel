package com.epam.tom.service;

import com.epam.tom.entity.TextComponent;
import com.epam.tom.entity.impl.*;
import com.epam.tom.exception.IncorrectNestingException;
import org.junit.Assert;
import org.junit.Test;

public class TOMBuilderTest {

    @Test
    public void textModelShouldBeEqualsTestString() throws IncorrectNestingException {
        String testString = "\tTest first! Test two.\n" +
                "\tTest three! Test four.";

        Word word1 = new Word("Test");
        Word word2 = new Word("first");
        Symbol pm3 = new Symbol("!");
        Word word4 = new Word("Test");
        Word word5 = new Word("two");
        Symbol pm6 = new Symbol(".");
        Word word7 = new Word("Test");
        Word word8 = new Word("three");
        Symbol pm9 = new Symbol("!");
        Word word10 = new Word("Test");
        Word word11 = new Word("four");
        Symbol pm12 = new Symbol(".");

        Lexeme lexeme1 = new Lexeme();
        Lexeme lexeme2 = new Lexeme();
        Lexeme lexeme3 = new Lexeme();
        Lexeme lexeme4 = new Lexeme();
        Lexeme lexeme5 = new Lexeme();
        Lexeme lexeme6 = new Lexeme();
        Lexeme lexeme7 = new Lexeme();
        Lexeme lexeme8 = new Lexeme();
        Offer offer1 = new Offer();
        Offer offer2 = new Offer();
        Offer offer3 = new Offer();
        Offer offer4 = new Offer();
        Paragraph paragraph1 = new Paragraph();
        Paragraph paragraph2 = new Paragraph();
        Text text = new Text();

        TOMBuilder builder = new TOMBuilder();
        builder.append(text);
        builder.append(paragraph1);

        builder.append(offer1);
        builder.append(lexeme1);
        builder.append(word1);
        builder.append(lexeme2);
        builder.append(word2);
        builder.append(pm3);

        builder.append(offer2);
        builder.append(lexeme3);
        builder.append(word4);
        builder.append(lexeme4);
        builder.append(word5);
        builder.append(pm6);

        builder.append(paragraph2);

        builder.append(offer3);
        builder.append(lexeme5);
        builder.append(word7);
        builder.append(lexeme6);
        builder.append(word8);
        builder.append(pm9);

        builder.append(offer4);
        builder.append(lexeme7);
        builder.append(word10);
        builder.append(lexeme8);
        builder.append(word11);
        builder.append(pm12);

        TextComponent component = builder.getComponent();
        Assert.assertEquals(testString,component.getContent());

    }

    @Test(expected = IncorrectNestingException.class)
    public void shouldTryToAddLexemeWhenExpectParagraphAndThrowException() throws IncorrectNestingException {
        TOMBuilder builder = new TOMBuilder();
        builder.append(new Text());
        builder.append(new Paragraph());
        builder.append(new Paragraph());
        builder.append(new Lexeme());

    }

    @Test(expected = IncorrectNestingException.class)
    public void shouldTryToAppendParagraphWhenRootElementIsParagraphAndThrowException() throws IncorrectNestingException {
        TOMBuilder builder = new TOMBuilder(new Paragraph());
        builder.append(new Paragraph());
    }
}
