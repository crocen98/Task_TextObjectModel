package com.epam.tom.service.interpret;

import com.epam.tom.exception.NotSupportedOperatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConverterToReversePolishNotation implements ExpressionConverter {
    private static final Logger LOGGER = LogManager.getLogger(ConverterToReversePolishNotation.class);


    private static final String PARENTHESES_REGEX = "\\(|\\)";
    private static final String BIT_OPERATORS_REGEX = "<<|>>|\\^|\\||\\&";
    private static final String NUMBERS_REGEX = "\\d+";
    private static final String SEPARATOR_REGEX = NUMBERS_REGEX + "|" + BIT_OPERATORS_REGEX + "|" + PARENTHESES_REGEX;
    private static ConverterToReversePolishNotation ourInstance = new ConverterToReversePolishNotation();

    private ConverterToReversePolishNotation() {
    }

    public static ConverterToReversePolishNotation getInstance() {
        return ourInstance;
    }

    @Override
    public List<String> convert(String expression) throws NotSupportedOperatorException {
        List<String> numbersAndOperators = splitExpression(expression);
        OperatorPriority priorityCalculator = OperatorPriority.getInstance();

        List<String> postfix = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();

        for (String value : numbersAndOperators) {

            if (value.matches(NUMBERS_REGEX)) {
                postfix.add(value);
            } else if (value.equals("(")) {
                stack.push(value);
            } else if (value.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    String operator = stack.pop();
                    postfix.add(operator);
                }
                stack.pop();
            } else if (value.matches(BIT_OPERATORS_REGEX)) {
                doOperatorAlgorithm(priorityCalculator, value, stack, postfix);
            }

        }
        while (!stack.isEmpty()) {
            String lastItems = stack.pop();
            postfix.add(lastItems);
        }
        LOGGER.info(expression + " in polish natation: " + postfix);
        return postfix;
    }

    private List<String> splitExpression(String expression) {
        List<String> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder(expression);
        Pattern pattern = Pattern.compile(SEPARATOR_REGEX);
        Matcher matcher = pattern.matcher(builder);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            list.add(builder.substring(start, end));
        }
        return list;
    }


    private void doOperatorAlgorithm(OperatorPriority priorityCalculator, String value, Deque<String> stack, List<String> postfix) throws NotSupportedOperatorException {
        int valuePriority = priorityCalculator.priority(value);
        int stackOperandPriority = 0;
        if (!stack.isEmpty()) {
            stackOperandPriority = priorityCalculator.priority(stack.peek());
        }
        while (valuePriority <= stackOperandPriority && !stack.isEmpty()) {
            postfix.add(stack.pop());
            if (stack.size() != 0) {
                stackOperandPriority = priorityCalculator.priority(stack.peek());
            }
        }
        stack.push(value);
    }

}


