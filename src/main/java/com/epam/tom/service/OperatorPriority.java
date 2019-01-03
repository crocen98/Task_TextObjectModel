package com.epam.tom.service;

public class OperatorPriority {
    private static OperatorPriority ourInstance = new OperatorPriority();

    public static OperatorPriority getInstance() {
        return ourInstance;
    }

    private OperatorPriority() {
    }

    public int priority(String operator){
        switch (operator){
            case "|": return 1;
            case "^": return 2;
            case "&": return 3;
            case ">>": return 4;
            case "<<": return 5;
        }
        return 0;
    }
}
