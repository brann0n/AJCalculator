package com.brandon.calculator.beans;

import com.brandon.calculator.exceptions.CalculatorException;

public abstract class AbstractNotationBean implements NotationBean {
    protected double calculate(double digit1, double digit2, String operator) throws CalculatorException {
        switch(operator){
            case("+"): return digit1 + digit2;
            case("-"): return digit1 - digit2;
            case("*"): return digit1 * digit2;
            case("/"):
                if (digit2 == 0) throw new CalculatorException("Cannot divide by zero");
                return digit1 / digit2;
            default: throw new CalculatorException("Unknown Operator: " + operator);
        }
    }
}
