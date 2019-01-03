package com.epam.tom.entity;

import com.epam.tom.entity.impl.*;
import org.junit.Assert;
import org.junit.Test;

public class CompositTOMIT {

    @Test
    public void textModelShouldBeEqualsTestString(){
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
        lexeme1.append(word1);

        Lexeme lexeme2 = new Lexeme();
        lexeme2.append(word2);
        lexeme2.append(pm3);

        Lexeme lexeme3 = new Lexeme();
        lexeme3.append(word4);

        Lexeme lexeme4 = new Lexeme();
        lexeme4.append(word5);
        lexeme4.append(pm6);

        Lexeme lexeme5 = new Lexeme();
        lexeme5.append(word7);

        Lexeme lexeme6 = new Lexeme();
        lexeme6.append(word8);
        lexeme6.append(pm9);

        Lexeme lexeme7 = new Lexeme();
        lexeme7.append(word10);

        Lexeme lexeme8 = new Lexeme();
        lexeme8.append(word11);
        lexeme8.append(pm12);


        Offer offer1 = new Offer();
        offer1.append(lexeme1);
        offer1.append(lexeme2);

        Offer offer2 = new Offer();
        offer2.append(lexeme3);
        offer2.append(lexeme4);

        Offer offer3 = new Offer();
        offer3.append(lexeme5);
        offer3.append(lexeme6);

        Offer offer4 = new Offer();
        offer4.append(lexeme7);
        offer4.append(lexeme8);


        /////////////////////

        Paragraph paragraph1 = new Paragraph();
        paragraph1.append(offer1);
        paragraph1.append(offer2);

        Paragraph paragraph2 = new Paragraph();
        paragraph2.append(offer3);
        paragraph2.append(offer4);

        ////////////////////////////

        Text text = new Text();

        text.append(paragraph1);
        text.append(paragraph2);

        System.out.println(text.getContent());
        Assert.assertEquals(testString,text.getContent());
    }
}
