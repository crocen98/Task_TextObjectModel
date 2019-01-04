package com.epam.tom.service.parser.impl;

import com.epam.tom.entity.TextComponent;
import com.epam.tom.entity.impl.Paragraph;
import com.epam.tom.exception.IncorrectNestingException;
import com.epam.tom.exception.NotSupportedOperatorException;
import com.epam.tom.service.TOMBuilder;
import com.epam.tom.service.parser.BaseParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends BaseParser {
    private static final String PARAGRAPH_SEPARATOR_REGEX = "[A-Z].+?((\\.\\.\\.)|!|\\.|\\?)";


    @Override
    public TextComponent parse(TOMBuilder builder, String text) throws IncorrectNestingException, NotSupportedOperatorException {
        if(text == null){
            return builder.getComponent();
        }
        Paragraph paragraphNode = new Paragraph();
        builder.append(paragraphNode);
        Pattern pattern = Pattern.compile(PARAGRAPH_SEPARATOR_REGEX);
        Matcher matcher = pattern.matcher(text);
        List<String> offers = new ArrayList<>();
        separateParagraph(matcher, offers, text);

        for(String oneOffer: offers){
            super.nextStage(builder,oneOffer);
        }

        return builder.getComponent();
    }

    private void separateParagraph(Matcher matcher, List<String> offers, String text) {
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String offer = text.substring(start, end);
            offers.add(offer);
        }
    }


}
