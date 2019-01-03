package com.epam.tom.service.parser.impl;

import com.epam.tom.entity.TextComponent;
import com.epam.tom.entity.impl.Offer;

import com.epam.tom.exception.IncorrectNestingException;
import com.epam.tom.service.TOMBuilder;
import com.epam.tom.service.parser.BaseParser;

import java.util.regex.Pattern;

public class OfferParser extends BaseParser {
    private static final String OFFER_SEPARATOR_REGEX = " +";


    @Override
    public TextComponent parse(TOMBuilder builder, String text) throws IncorrectNestingException {
        if(text == null){
            return builder.getComponent();
        }

        Offer offer = new Offer();
        builder.append(offer);
        String[]  lexemes = text.split(OFFER_SEPARATOR_REGEX);
        for(String lexeme: lexemes){
            super.nextStage(builder,lexeme);
        }
        return builder.getComponent();
    }


    public static void main(String ... args){
        String s = "It has survived - not only (five) centuries, but also the leap into 13<<2 electronc typesetting, remaining 3>>5 essentially 6&9|(3&4) unchanged.";

        Pattern pattern = Pattern.compile(OFFER_SEPARATOR_REGEX);
        String[]  str = s.split(OFFER_SEPARATOR_REGEX);

        for (String sss: str){
            System.out.println(sss);
        }

    }
}
