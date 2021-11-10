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
public class InfixNotationBean extends AbstractNotationBean {

    public InfixNotationBean() {

    }

    @Override
    public double handleSum(String sumText) throws CalculatorException {
        //1 1 +
        List<String> expressions = new ArrayList<>();
        for (String s : sumText.split(" ")) {
            expressions.add(s);
        }

        //check if the expressions array has a minimal of 3 elements
        if (expressions.size() > 2) {

            while (expressions.size() != 1) {
                int highestOperatorIndex;
                String highestOperator;

                for (int i = 0; i < expressions.size(); i++) {
                    return 10.0;
                }
            }
        }

        return 0;
    }

    protected boolean isHigherOperatorRank(String operator, String previousOperator) {
        String addition = "+-";

        int previousRank = addition.contains(previousOperator) ? 0 : 1;
        int currentRank = addition.contains(operator) ? 0 : 1;

        return currentRank > previousRank;
    }


}
