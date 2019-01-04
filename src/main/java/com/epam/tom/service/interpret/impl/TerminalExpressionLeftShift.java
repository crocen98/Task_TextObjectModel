package com.epam.tom.service.interpret.impl;

import com.epam.tom.service.interpret.BitExpression;
import com.epam.tom.service.interpret.Context;

public class TerminalExpressionLeftShift implements BitExpression {

    @Override
    public void interpret(Context context) {
        Integer firstOperand = context.pop();
        Integer secondOperand = context.pop();
        Integer result = secondOperand << firstOperand;
        context.push(result);
    }
}
