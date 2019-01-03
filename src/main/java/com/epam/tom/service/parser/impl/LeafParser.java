package com.epam.tom.service.parser.impl;

import com.epam.tom.entity.TextComponent;
import com.epam.tom.entity.impl.Symbol;
import com.epam.tom.entity.impl.Word;
import com.epam.tom.exception.IncorrectNestingException;
import com.epam.tom.service.TOMBuilder;
import com.epam.tom.service.parser.BaseParser;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LeafParser extends BaseParser {
    private static final String WORD_REGX = "[\\w](\\w|-\\w+)*";
    private final String SYMBOL_REGX = "[!:;(),\\.—]|(\\.\\.\\.)";
    private final String LOGIC_EXPRESSION = "(\\(+|\\d+)(\\d+|<<|>>|\\^|&|\\||\\)+|\\(+)+(\\)+|\\d+)";
    @Override
    public TextComponent parse(TOMBuilder builder, String text) throws IncorrectNestingException {
        if(text == null){
            return builder.getComponent();
        }

        Pattern patternWord = Pattern.compile(WORD_REGX);
        Matcher matcherWord = patternWord.matcher(text);

        Pattern patternLogicExpression = Pattern.compile(LOGIC_EXPRESSION);
        Matcher matcherLogicExpression = patternLogicExpression.matcher(text);

        Pattern patternSymbol = Pattern.compile(SYMBOL_REGX);
        Matcher matcherSymbol = patternSymbol.matcher(text);

        TextComponent leafNode= null;
        if(matcherLogicExpression.matches()){
            leafNode = new Word(text);
        } else if( matcherSymbol.matches()){
            leafNode = new Symbol(text);
        } else {
            leafNode = new Word(text);
        }

        builder.append(leafNode);

        return builder.getComponent();
    }



}
