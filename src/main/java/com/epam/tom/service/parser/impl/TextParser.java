package com.epam.tom.service.parser.impl;

import com.epam.tom.entity.TextComponent;
import com.epam.tom.entity.impl.Text;
import com.epam.tom.exception.IncorrectNestingException;
import com.epam.tom.service.TOMBuilder;
import com.epam.tom.service.parser.BaseParser;


public class TextParser extends BaseParser {

    private static final String TEXT_SEPARATOR_REGEX = "\t";


    @Override
    public TextComponent parse(TOMBuilder builder, String text) throws IncorrectNestingException {
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


    public static void main(String ... args){
        String s = "\tIt has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 3>>5 essentially 6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(2^5|6&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here, making it look like readable English.\n" +
                "\tIt is a (8^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.\n" +
                "\tBye.";

        String[] arr = s.split(TEXT_SEPARATOR_REGEX);
        for(String st :arr){
            System.out.println("STRING" + st + "\n\n");
        }
    }
}
