package com.epam.tom.service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConverterToReversePolishNotation implements StringConverter {

    private static final String BIT_OPERATORS_REGEX = "\\d+|<<|>>|\\^|\\||\\&|\\(|\\)";
    private static ConverterToReversePolishNotation ourInstance = new ConverterToReversePolishNotation();

    public static ConverterToReversePolishNotation getInstance() {
        return ourInstance;
    }

    private ConverterToReversePolishNotation() {
    }


    @Override
    public  List<String> convert(String expression) {
        List<String> numbersAndOperators = splitExpression(expression);
        //////////////

//            List<String> postfix = new ArrayList<String>();
//            Deque<String> stack = new ArrayDeque<String>();
//            StringTokenizer tokenizer = new StringTokenizer(infix, delimiters, true);
//            String prev = "";
//            String curr = "";
//            for(String value: numbersAndOperators ) {
//
//
//                else if (isDelimiter(curr)) {
//                    if (curr.equals("(")) stack.push(curr);
//                    else if (curr.equals(")")) {
//                        while (!stack.peek().equals("(")) {
//                            postfix.add(stack.pop());
//                            if (stack.isEmpty()) {
//                                System.out.println("Скобки не согласованы.");
//                                flag = false;
//                                return postfix;
//                            }
//                        }
//                        stack.pop();
//                        if (!stack.isEmpty() && isFunction(stack.peek())) {
//                            postfix.add(stack.pop());
//                        }
//                    }
//                    else {
//                        if (curr.equals("-") && (prev.equals("") || (isDelimiter(prev)  && !prev.equals(")")))) {
//                            // унарный минус
//                            curr = "u-";
//                        }
//                        else {
//                            while (!stack.isEmpty() && (priority(curr) <= priority(stack.peek()))) {
//                                postfix.add(stack.pop());
//                            }
//
//                        }
//                        stack.push(curr);
//                    }
//
//                }
//
//                else {
//                    postfix.add(curr);
//                }
//                prev = curr;
//            }
//
//            while (!stack.isEmpty()) {
//                if (isOperator(stack.peek())) postfix.add(stack.pop());
//                else {
//                    System.out.println("Скобки не согласованы.");
//                    flag = false;
//                    return postfix;
//                }
//            }
//            return postfix;
//        }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//        ////////////
//        return numbersAndOperators;
        return null;
    }


    private List<String> splitExpression(String expression){
        List<String> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder(expression);
        Pattern pattern = Pattern.compile(BIT_OPERATORS_REGEX);
        Matcher matcher = pattern.matcher(builder);
        while (matcher.find()){
            int start = matcher.start();
            int end = matcher.end();
            list.add(builder.substring(start,end));
        }
        return list;
    }


    public static void main(String ... args){
        ConverterToReversePolishNotation converter = new ConverterToReversePolishNotation();
        List<String> ob = converter.splitExpression("(8^5|1&2<<(2|5>>2&71))|1200");
        System.out.println(ob);
    }
}
