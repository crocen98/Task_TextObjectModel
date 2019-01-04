package com.epam.tom.service.parser.impl;

import com.epam.tom.entity.TextComponent;
import com.epam.tom.entity.impl.Symbol;
import com.epam.tom.entity.impl.Word;
import com.epam.tom.exception.IncorrectNestingException;
import com.epam.tom.exception.NotSupportedOperatorException;
import com.epam.tom.service.TOMBuilder;
import com.epam.tom.service.interpret.BitInterpreter;
import com.epam.tom.service.interpret.Interpreter;
import com.epam.tom.service.parser.BaseParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LeafParser extends BaseParser {

    private static final String SYMBOL_REGEX = "[!:;(),\\.\u2014]|(\\.\\.\\.)";
    private static final String BIT_EXPRESSION = "(\\(+|\\d+)(\\d+|<<|>>|\\^|&|\\||\\)+|\\(+)+(\\)+|\\d+)";
    private Interpreter interpreter = new BitInterpreter();
    @Override
    public TextComponent parse(TOMBuilder builder, String text) throws IncorrectNestingException, NotSupportedOperatorException {
        if(text == null){
            return builder.getComponent();
        }
        Pattern patternLogicExpression = Pattern.compile(BIT_EXPRESSION);
        Matcher matcherLogicExpression = patternLogicExpression.matcher(text);
        Pattern patternSymbol = Pattern.compile(SYMBOL_REGEX);
        Matcher matcherSymbol = patternSymbol.matcher(text);

        TextComponent leafNode;
        if(matcherLogicExpression.matches()){
            int number = interpreter.calculate(text);
            leafNode = new Word(Integer.toString(number));
        } else if( matcherSymbol.matches()){
            leafNode = new Symbol(text);
        } else {
            leafNode = new Word(text);
        }
        builder.append(leafNode);
        return builder.getComponent();
    }
}
