package com.brandon.calculator.beans;

import com.brandon.calculator.beans.InfixNotationBean;
import com.brandon.calculator.exceptions.CalculatorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class InfixTest {
    private InfixNotationBean bean;

    @BeforeEach
    public void setup(){
        bean = new InfixNotationBean();
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
