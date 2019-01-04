package com.epam.tom.service.parser.impl;

import com.epam.tom.entity.TextComponent;
import com.epam.tom.entity.impl.Offer;
import com.epam.tom.exception.IncorrectNestingException;
import com.epam.tom.exception.NotSupportedOperatorException;
import com.epam.tom.service.TOMBuilder;
import com.epam.tom.service.parser.BaseParser;


public class OfferParser extends BaseParser {
    private static final String OFFER_SEPARATOR_REGEX = " +";


    @Override
    public TextComponent parse(TOMBuilder builder, String text) throws IncorrectNestingException, NotSupportedOperatorException {
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

}
