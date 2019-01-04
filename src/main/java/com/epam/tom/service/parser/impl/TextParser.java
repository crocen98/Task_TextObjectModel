package com.epam.tom.service.parser.impl;

import com.epam.tom.entity.TextComponent;
import com.epam.tom.entity.impl.Text;
import com.epam.tom.exception.IncorrectNestingException;
import com.epam.tom.exception.NotSupportedOperatorException;
import com.epam.tom.service.TOMBuilder;
import com.epam.tom.service.parser.BaseParser;


public class TextParser extends BaseParser {

    private static final String TEXT_SEPARATOR_REGEX = "\t";

    @Override
    public TextComponent parse(TOMBuilder builder, String text) throws IncorrectNestingException, NotSupportedOperatorException {
        if(text == null){
            return builder.getComponent();
        }
        Text textNode = new Text();
        builder.append(textNode);
        String[] paragraphs = text.split(TEXT_SEPARATOR_REGEX);

        for(int i = 1; i < paragraphs.length; ++i) {
            nextStage(builder,paragraphs[i]);
        }

        return builder.getComponent();
    }
}
