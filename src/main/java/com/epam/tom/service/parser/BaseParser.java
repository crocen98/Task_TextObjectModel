package com.epam.tom.service.parser;


import com.epam.tom.exception.IncorrectNestingException;
import com.epam.tom.service.TOMBuilder;

public abstract class BaseParser implements Parser {
    private Parser next;

    protected void nextStage(TOMBuilder builder,String fragment) throws IncorrectNestingException {
        if(next != null){
            next.parse(builder,fragment);
        }
    }

    @Override
    public Parser linkNext(Parser chain) {
        next = chain;
        return next;
    }
}
