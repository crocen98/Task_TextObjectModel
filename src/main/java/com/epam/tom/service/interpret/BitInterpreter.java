package com.epam.tom.service.interpret;

import com.epam.tom.exception.NotSupportedOperatorException;
import com.epam.tom.service.interpret.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class BitInterpreter implements Interpreter {
    private static final Logger LOGGER = LogManager.getLogger(BitInterpreter.class);

    private List<BitExpression> listExpression = new ArrayList<>();
    private ExpressionConverter converter;

    public BitInterpreter(ExpressionConverter converter) {
        this.converter = converter;
    }

    public BitInterpreter() {
        this.converter = ConverterToReversePolishNotation.getInstance();
    }

    @Override
    public int calculate(String expression) throws NotSupportedOperatorException {
        LOGGER.info("Start calculate bitExpression: " + expression);
        List<String> polishNotification = converter.convert(expression);
        List<BitExpression> listExpression;
        listExpression = listInitializer(polishNotification);
        Context context = new Context();
        for (BitExpression terminal : listExpression) {
            terminal.interpret(context);
        }
        int result = context.pop();
        LOGGER.info("Expression: " + expression + " equals " + result);

        return result;
    }

    private List<BitExpression> listInitializer(List<String> polishNotification) {
        List<BitExpression> listExpression = new ArrayList<>();
        for (String value : polishNotification) {
            BitExpression expression;
            switch (value) {
                case "^":
                    expression = new TerminalExpressionXOR();
                    break;
                case ">>":
                    expression = new TerminalExpressionRightShift();
                    break;
                case "|":
                    expression = new TerminalExpressionOR();
                    break;
                case "<<":
                    expression = new TerminalExpressionLeftShift();
                    break;
                case "&":
                    expression = new TerminalExpressionAND();
                    break;

                default:
                    Integer intValue = Integer.valueOf(value);
                    expression = new NonterminalExpressionNumber(intValue);
            }
            listExpression.add(expression);
        }
        return listExpression;
    }

}
