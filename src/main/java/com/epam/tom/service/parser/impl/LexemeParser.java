package com.epam.tom.service.parser.impl;

import com.epam.tom.entity.TextComponent;
import com.epam.tom.entity.impl.Lexeme;
import com.epam.tom.exception.IncorrectNestingException;
import com.epam.tom.exception.NotSupportedOperatorException;
import com.epam.tom.service.TOMBuilder;
import com.epam.tom.service.parser.BaseParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends BaseParser {

    private static final String WORD_REGEX = "[\\w](\\w|-\\w+)*";
    private static final String SYMBOL_PARSER = "[!:;(),\\.—]|(\\.\\.\\.)";
    private static final String BIT_EXPRESSION = "(\\(+|\\d+)(\\d+|<<|>>|\\^|&|\\||\\)+|\\(+)+(\\)+|\\d+)";
    private static final String LEAF_EXPRESSION = BIT_EXPRESSION + "|" + SYMBOL_PARSER + "|" + WORD_REGEX;
    @Override
    public TextComponent parse(TOMBuilder builder, String text) throws IncorrectNestingException, NotSupportedOperatorException {
        if(text == null){
            return builder.getComponent();
        }
        Lexeme lexemeNode = new Lexeme();
        builder.append(lexemeNode);
        Pattern pattern = Pattern.compile(LEAF_EXPRESSION);
        Matcher matcher = pattern.matcher(text);
        List<String> leafs = new ArrayList<>();

        separateOffer(matcher, leafs, text);

        for (String oneLeaf : leafs) {
            super.nextStage(builder, oneLeaf);
        }

        return builder.getComponent();
    }


    private void separateOffer(Matcher matcher, List<String> leafs, String text) {
        while (matcher.find()) {
            int start = matcher.start();
            int end;
            end = matcher.end();
            String leafNOde = text.substring(start,end);
            leafs.add(leafNOde);
        }

    }
}
