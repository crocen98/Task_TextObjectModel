package com.epam.tom.service.parser.impl;

import com.epam.tom.entity.TextComponent;
import com.epam.tom.entity.impl.Lexeme;
import com.epam.tom.entity.impl.Offer;
import com.epam.tom.exception.IncorrectNestingException;
import com.epam.tom.service.TOMBuilder;
import com.epam.tom.service.parser.BaseParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends BaseParser {

    private static final String WORD_REGX = "[\\w](\\w|-\\w+)*";
    private final String SYMBOL_PARSER = "[!:;(),\\.—]|(\\.\\.\\.)";
    private final String LOGIC_EXPRESSION = "(\\(+|\\d+)(\\d+|<<|>>|\\^|&|\\||\\)+|\\(+)+(\\)+|\\d+)";
    private final String LEAF_EXPRESSION = LOGIC_EXPRESSION +'|'+SYMBOL_PARSER+ '|' + WORD_REGX;
    @Override
    public TextComponent parse(TOMBuilder builder, String text) throws IncorrectNestingException {
        if(text == null){
            return builder.getComponent();
        }


        Lexeme lexemeNode = new Lexeme();
        builder.append(lexemeNode);
        Pattern pattern = Pattern.compile(LEAF_EXPRESSION);
        Matcher matcher = pattern.matcher(text);
        //separate Strings
        List<String> leafs = new ArrayList<>();
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String leafNOde = text.substring(start,end);
            leafs.add(leafNOde);
        }

        for(String oneLeaf: leafs){
            super.nextStage(builder,oneLeaf);
        }

        return builder.getComponent();
    }
}
