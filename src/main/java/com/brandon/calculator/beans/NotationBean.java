package com.brandon.calculator.beans;

import com.brandon.calculator.exceptions.CalculatorException;

public interface NotationBean {
    double handleSum(String sumText) throws CalculatorException;
}
