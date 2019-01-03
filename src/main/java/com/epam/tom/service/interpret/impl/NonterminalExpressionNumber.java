package com.epam.tom.service.interpret.impl;

import com.epam.tom.service.interpret.BitExpression;
import com.epam.tom.service.interpret.Context;

public class NonterminalExpressionNumber implements BitExpression {
    Integer number;

    public NonterminalExpressionNumber(Integer number) {
        this.number = number;
    }


    @Override
    public void interpret(Context context) {
        context.push(number);
    }
}
