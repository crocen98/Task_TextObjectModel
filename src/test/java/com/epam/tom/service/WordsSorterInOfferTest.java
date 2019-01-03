package com.epam.tom.service;

import com.epam.tom.entity.TextComponent;

import com.epam.tom.entity.impl.Lexeme;
import com.epam.tom.entity.impl.Symbol;
import com.epam.tom.entity.impl.TextPart;
import com.epam.tom.entity.impl.Word;
import com.epam.tom.exception.IncorrectNestingException;
import com.epam.tom.exception.NotSupportedSortException;
import com.epam.tom.service.comparator.WordLengthComparator;
import com.epam.tom.service.parser.impl.*;
import org.junit.Assert;
import org.junit.Test;

public class WordsSorterInOfferTest {

    private final WordsSorterInOffer sorter = new WordsSorterInOffer();


    @Test
    public void sorterSortAllWordsInOfferAndPassSymbols() throws IncorrectNestingException, NotSupportedSortException {

        OfferParser offerParser = new OfferParser();
        LexemeParser lexemeParser = new LexemeParser();
        offerParser.linkNext(lexemeParser);
        LeafParser leafParser = new LeafParser();
        lexemeParser.linkNext(leafParser);

        TOMBuilder builder = new TOMBuilder();

        String testString = "wwwwwwwwwwwwwwwwwwwwwwww-ww-w-w 22 It has survived — not only (five) centuries, but also the leap 1 into 13<<2 electronic typesetting, remaining 3>>5 essentially 6&9|(3&4) unchanged.";
        String expectedResult = "1 22 It has not — but the (only) five, also leap into 3>>5 13<<2 survived centuries remaining 6&9|(3&4), unchanged electronic typesetting essentially wwwwwwwwwwwwwwwwwwwwwwww-ww-w-w.";
        TextComponent offer =offerParser.parse(builder,testString);
        sorter.sort((TextPart) offer,new WordLengthComparator());
        Assert.assertEquals(expectedResult,offer.getContent());
    }


    @Test
    public void shouldSortWordsInEveryOfferInText() throws IncorrectNestingException, NotSupportedSortException {
        TextParser textParser = new TextParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        textParser.linkNext(paragraphParser);
        OfferParser offerParser = new OfferParser();
        paragraphParser.linkNext(offerParser);
        LexemeParser lexemeParser = new LexemeParser();
        offerParser.linkNext(lexemeParser);
        LeafParser leafParser = new LeafParser();
        lexemeParser.linkNext(leafParser);

        TOMBuilder builder = new TOMBuilder();
        String expectedString = "\tIt has not — but the (only) five, also leap into 3>>5 13<<2 survived centuries remaining, 6&9|(3&4) unchanged electronic typesetting essentially. It in of of was the the and with more with like Lorem Ipsum Aldus Lorem, Ipsum sheets release desktop Letraset passages recently software versions PageMaker including containing publishing popularised 5|(1&2&(3|(4&(2^5|6&47)|3)|2)|1).\n" +
                "\ta a a It is be by of at the its long fact that will page when reader layout content looking readable distracted established. a of is it of as to it The has that here here look like point, using Ipsum using normal (making letters), opposed Content, content English readable more-or-less distribution (71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78.\n" +
                "\ta a a It is be of at its fact that will page when reader layout looking established (8^5|1&2<<(2|5>>2&71))|1200.\n" +
                "\tBye.";
        String testString = "\tIt has survived — not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially 6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(2^5|6&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here, making it look like readable English.\n" +
                "\tIt is a (8^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.\n" +
                "\tBye.";
        TextComponent text =textParser.parse(builder,testString);
        sorter.sort((TextPart) text,new WordLengthComparator());
        Assert.assertEquals(expectedString,text.getContent());
    }

    @Test(expected = NotSupportedSortException.class)
    public void tryToSortWordsForLexemeAndThrowException() throws NotSupportedSortException {
        Lexeme lexeme = new Lexeme();
        Word word = new Word("First");
        Symbol symbol = new Symbol(".");
        lexeme.append(word);
        lexeme.append(symbol);
        sorter.sort(lexeme,new WordLengthComparator());
    }
}
