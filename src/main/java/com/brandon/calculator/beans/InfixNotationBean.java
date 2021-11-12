package com.brandon.calculator.beans;

import com.brandon.calculator.exceptions.CalculatorException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Profile("infix")
public class InfixNotationBean implements NotationBean {

    private CalculatorBean cBean;

    public InfixNotationBean(CalculatorBean cBean) {
        this.cBean = cBean;
    }

    @Override
    public double handleSum(String sumText) throws CalculatorException {
        if (sumContainsParenthesis(sumText)) {
            //follow a special handler first before doing the regular interpretation
            //overwrite the sumText variable to be without ( )

            sumText = calculateParenthesis(sumText);
        }

        return processRegularSum(sumText);
    }

    protected double processRegularSum(String sumText) throws CalculatorException {
        List<String> digits = new ArrayList<>(Arrays.asList(sumText.split("(\\+|-|\\*|\\/)")));
        List<String> operators = new ArrayList<>(Arrays.asList(sumText.split("(\\d+(\\.\\d+)?|\\s)")));
        operators.removeAll(Arrays.asList("", null));

        //check if operator and digits
        if (digits.size() != operators.size() + 1) {
            throw new CalculatorException("The amount of operators is not correct for the amount of numbers provided.");
        }
        // 20 + 20 * 10
        while (digits.size() > 1) {
            //first search the operator with the highest rank.
            int highestOperatorIndex = highestOperatorIndex(operators);

            double first = Double.parseDouble(digits.get(highestOperatorIndex));
            double second = Double.parseDouble(digits.get(highestOperatorIndex + 1));

            double answer = cBean.calculate(first, second, operators.get(highestOperatorIndex).charAt(0));

            operators.remove(highestOperatorIndex);
            digits.remove(highestOperatorIndex + 1);
            digits.set(highestOperatorIndex, String.valueOf(answer));
        }

        return Double.parseDouble(digits.get(0));
    }

    protected int highestOperatorIndex(List<String> operators) {
        int highestIndex = 0;
        for (int i = 1; i < operators.size(); i++) {
            if (isHigherOperatorRank(operators.get(i), operators.get(i - 1))) {
                highestIndex = i;
            }
        }

        return highestIndex;
    }

    protected boolean isHigherOperatorRank(String operator, String previousOperator) {
        String addition = "+-";

        int previousRank = addition.contains(previousOperator) ? 0 : 1;
        int currentRank = addition.contains(operator) ? 0 : 1;

        return currentRank > previousRank;
    }

    private boolean sumContainsParenthesis(String sumText) {
        return sumText.contains("(") || sumText.contains(")");
    }

    private String calculateParenthesis(String sumText) throws CalculatorException {
        int startIndex = sumText.indexOf('(');
        int endIndex = sumText.indexOf(')');

        int checkDoubleParenthesis = sumText.lastIndexOf('(');
        if (startIndex == checkDoubleParenthesis) {
            String sumInParenthesis = sumText.substring(startIndex, endIndex + 1);
            String sumWithoutParenthesis = sumInParenthesis.substring(1, sumInParenthesis.length() - 1);
            double answer = processRegularSum(sumWithoutParenthesis);
            return sumText.replace(sumInParenthesis, Double.toString(answer));
        } else if (checkDoubleParenthesis > startIndex && checkDoubleParenthesis < endIndex) {
            //the other ( is within the ( )
        } else {
            //ignore for now, the other ( is after ( )
        }
        return "";
    }
}
