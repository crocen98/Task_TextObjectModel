package com.epam.tom.service.interpret;


import com.epam.tom.exception.NotSupportedOperatorException;

import java.util.List;


public interface ExpressionConverter {
    List<String> convert(String expression) throws NotSupportedOperatorException;
}
