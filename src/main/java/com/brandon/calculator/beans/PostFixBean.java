package com.brandon.calculator.beans;

import com.brandon.calculator.exceptions.CalculatorException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Profile("postfix")
public class PostFixBean implements NotationBean {

    private CalculatorBean cBean;

    public PostFixBean(CalculatorBean cBean) {
        this.cBean = cBean;
    }

    @Override
    public double handleSum(String sumText) throws CalculatorException {
        List<String> functions = new ArrayList<>(Arrays.asList(sumText.split(" "))); //10 10 + 10 10 * *
        List<String> completedFunctions = recursiveHandlePostFix(functions);

        if (completedFunctions.size() == 1) {
            return Double.parseDouble(completedFunctions.get(0));
        }

        throw new CalculatorException("Something went wrong!");
    }

    protected List<String> recursiveHandlePostFix(List<String> functionArray) throws CalculatorException {
        if (functionArray.size() == 1) return functionArray;

        for (int i = 0; i < functionArray.size(); i++) {
            if (cBean.isOperator(functionArray.get(i))) {
                //take the previous 2 indexes and use the current operator to calculate

                if (i < 2) throw new CalculatorException("Invalid operator position");

                double left = Double.parseDouble(functionArray.get(i - 2));
                double right = Double.parseDouble(functionArray.get(i - 1));
                char operator = functionArray.get(i).charAt(0);

                double result = cBean.calculate(left, right, operator);

                functionArray.set(i - 2, String.valueOf(result));
                functionArray.remove(i);
                functionArray.remove(i - 1);

                return recursiveHandlePostFix(functionArray);
            }
        }

        return Collections.emptyList();
    }

}
