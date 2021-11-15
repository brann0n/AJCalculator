package com.brandon.calculator.beans;

import com.brandon.calculator.exceptions.CalculatorException;
import org.springframework.stereotype.Component;

@Component
public class CalculatorBean {
    public double calculate(double left, double right, char operator) throws CalculatorException {
        switch (operator) {
            case ('+'):
                return left + right;
            case ('-'):
                return left - right;
            case ('*'):
                return left * right;
            case ('/'):
                if (right == 0) throw new CalculatorException("Cannot divide by zero");
                return left / right;
            default:
                throw new CalculatorException("Unknown Operator: " + operator);
        }
    }
}
