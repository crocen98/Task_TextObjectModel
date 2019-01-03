package com.epam.tom.service.parser.impl;

import com.epam.tom.entity.TextComponent;
import com.epam.tom.entity.impl.Paragraph;
import com.epam.tom.entity.impl.Text;
import com.epam.tom.exception.IncorrectNestingException;
import com.epam.tom.service.TOMBuilder;
import com.epam.tom.service.parser.BaseParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends BaseParser {
    private static final String PARAGRAPH_SEPARATOR_REGEX = "[A-Z].+?((\\.\\.\\.)|!|\\.|\\?)";


    @Override
    public TextComponent parse(TOMBuilder builder, String text) throws IncorrectNestingException {
        if(text == null){
            return builder.getComponent();
        }


        Paragraph paragraphNode = new Paragraph();
        builder.append(paragraphNode);
        Pattern pattern = Pattern.compile(PARAGRAPH_SEPARATOR_REGEX);
        Matcher matcher = pattern.matcher(text);
       //separate Strings
        List<String> offers = new ArrayList<>();
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String offer = text.substring(start,end);
            offers.add(offer);
        }

        for(String oneOffer: offers){
            super.nextStage(builder,oneOffer);
        }

        return builder.getComponent();
    }


    public static void main(String ... args){
        String s = "It has survived - not only (five) centuries, but also the leap into 13<<2 electronc typesetting, remaining 3>>5 essentially 6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(2^5|6&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum!\n";

        Pattern pattern = Pattern.compile(PARAGRAPH_SEPARATOR_REGEX);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            System.out.println(s.substring(start, end));
        }


    }
}
