package com.epam.tom.service.interpret.impl;

import com.epam.tom.service.interpret.Context;
import com.epam.tom.service.interpret.BitExpression;

public class TerminalExpressionRightShift implements BitExpression {

    @Override
    public void interpret(Context context) {
        Integer firstOperand = context.pop();
        Integer secondOperand = context.pop();
        Integer result = firstOperand >> secondOperand;
        context.push(result);
    }
}