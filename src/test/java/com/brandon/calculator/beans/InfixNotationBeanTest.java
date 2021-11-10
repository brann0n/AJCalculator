package com.brandon.calculator.beans;

import com.brandon.calculator.exceptions.CalculatorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class InfixNotationBeanTest {
    private InfixNotationBean bean;
    @Mock
    private CalculatorBean calculatorBeanMock;

    @BeforeEach
    public void setup(){
        openMocks(this);
        bean = new InfixNotationBean(calculatorBeanMock);
    }

    @Test
    public void testOperatorRanker(){
        assertThat(bean.isHigherOperatorRank("*", "-")).isEqualTo(true);
        assertThat(bean.isHigherOperatorRank("+", "-")).isEqualTo(false);
        assertThat(bean.isHigherOperatorRank("+", "*")).isEqualTo(false);
        assertThat(bean.isHigherOperatorRank("+", "+")).isEqualTo(false);
    }

    @Test
    public void testCalculate() throws CalculatorException {

        assertThat(bean.handleSum("10 + 10")).isEqualTo(20.0);
    }
}
