package com.epam.tom.service.parser;

import com.epam.tom.entity.TextComponent;
import com.epam.tom.exception.IncorrectNestingException;
import com.epam.tom.exception.NotSupportedOperatorException;
import com.epam.tom.service.TOMBuilder;

public interface Parser {
    TextComponent parse(TOMBuilder builder, String text) throws IncorrectNestingException, NotSupportedOperatorException;
    Parser linkNext(Parser parser);
}
