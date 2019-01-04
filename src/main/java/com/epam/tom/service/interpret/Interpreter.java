package com.epam.tom.service.interpret;

import com.epam.tom.exception.NotSupportedOperatorException;

public interface Interpreter {
    int calculate(String expression) throws NotSupportedOperatorException;
}
