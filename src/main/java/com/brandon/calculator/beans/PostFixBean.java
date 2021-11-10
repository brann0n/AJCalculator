package com.brandon.calculator.beans;

import com.brandon.calculator.exceptions.CalculatorException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Profile("postfix")
public class PostFixBean extends AbstractNotationBean {
    @Override
    public double handleSum(String sumText) throws CalculatorException {
        String[] functions = sumText.split(" "); //1 1 +
        List<Double> digits = Arrays.stream(functions).map(i -> i.matches("\\d+") ? Double.parseDouble(i) : null).filter(Objects::nonNull).collect(Collectors.toList());
        List<String> operators = Arrays.stream(functions).map(i -> i.matches("\\D+") ? i : null).filter(Objects::nonNull).collect(Collectors.toList());
        if (digits.size() == operators.size() + 1) {
            while (digits.size() > 1) {
                double lastDigit = digits.get(digits.size() - 1);
                double secondLastDigit = digits.get(digits.size() - 2);
                String operator = operators.get(0);

                digits.remove(digits.size() - 1);
                operators.remove(0);
                digits.set(digits.size() - 1, calculate(secondLastDigit, lastDigit, operator));
            }

            return digits.get(0);
        } else {
            throw new CalculatorException("Invalid operator count");
        }
    }


}
