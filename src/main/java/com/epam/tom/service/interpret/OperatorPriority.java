package com.epam.tom.service.interpret;

import com.epam.tom.exception.NotSupportedOperatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OperatorPriority {
    private static final Logger LOGGER = LogManager.getLogger(OperatorPriority.class);
    private static OperatorPriority ourInstance = new OperatorPriority();

    private OperatorPriority() {
    }

    public static OperatorPriority getInstance() {
        return ourInstance;
    }

    public int priority(String operator) throws NotSupportedOperatorException {
        switch (operator) {
            case "(":
                return 1;
            case "|":
                return 2;
            case "^":
                return 3;
            case "&":
                return 4;
            case ">>":
                return 5;
            case "<<":
                return 6;
            default:
                try {
                    throw new NotSupportedOperatorException("Operator: " + operator + " not support");
                } catch (NotSupportedOperatorException e) {
                    LOGGER.error(e);
                    throw e;
                }
        }
    }
}

