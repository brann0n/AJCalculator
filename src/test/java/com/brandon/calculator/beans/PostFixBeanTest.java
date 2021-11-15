package com.brandon.calculator.beans;

import com.brandon.calculator.exceptions.CalculatorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class PostFixBeanTest {
    private PostFixBean bean;

    @Mock
    private CalculatorBean calculatorBeanMock;

    @BeforeEach
    public void setup() throws CalculatorException {
        openMocks(this);
        when(calculatorBeanMock.calculate(10.0, 10.0, '+')).thenReturn(20d);
        when(calculatorBeanMock.calculate(10.0, 10.0, '*')).thenReturn(100d);
        bean = new PostFixBean(calculatorBeanMock);
    }

    @Test
    public void testCalculate() throws CalculatorException {
        //test some proper calculations
        assertThat(bean.handleSum("10 10 +")).isEqualTo(20.0);
        assertThat(bean.handleSum("10 10 *")).isEqualTo(100.0);
        assertThat(bean.handleSum("10")).isEqualTo(10d);

        //test with too many operators
        assertThatThrownBy(() -> {
            bean.handleSum("10 10 + *");
        }).isInstanceOf(CalculatorException.class).hasMessage("Invalid operator position");

        //test with operator in wrong position
        assertThatThrownBy(() -> {
            bean.handleSum("+ 10 10");
        }).isInstanceOf(CalculatorException.class).hasMessage("Invalid operator position");

        //test two numbers and no operators
        assertThatThrownBy(() -> {
            bean.handleSum("10 10");
        }).isInstanceOf(CalculatorException.class).hasMessage("Something went wrong!");

        //test infix notation
        assertThatThrownBy(() -> {
            bean.handleSum("10 + 10");
        }).isInstanceOf(CalculatorException.class).hasMessage("Invalid operator position");

        //test with forgotten spaces
        assertThatThrownBy(() -> {
            bean.handleSum("10 10* +");
        }).isInstanceOf(NumberFormatException.class);

        //test with only operator
        assertThatThrownBy(() -> {
            bean.handleSum("*");
        }).isInstanceOf(CalculatorException.class).hasMessage("Something went wrong!");
    }

    @Test
    void isOperator() {
        CalculatorBean c = new CalculatorBean();
        assertThat(bean.isOperator("+")).isEqualTo(true);
        assertThat(bean.isOperator("*")).isEqualTo(true);
        assertThat(bean.isOperator("**")).isEqualTo(false);
        assertThat(bean.isOperator("1")).isEqualTo(false);
        assertThat(bean.isOperator("%")).isEqualTo(false);
        assertThat(bean.isOperator("A")).isEqualTo(false);
    }
}
