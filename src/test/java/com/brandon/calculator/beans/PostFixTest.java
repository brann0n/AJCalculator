package com.brandon.calculator.beans;

import com.brandon.calculator.exceptions.CalculatorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class PostFixTest {
    private PostFixBean bean;

    @Mock
    private CalculatorBean calculatorBeanMock;

    @BeforeEach
    public void setup() throws CalculatorException {
        openMocks(this);
        when(calculatorBeanMock.calculate(10d, 10d, '+')).thenReturn(20d);
        bean = new PostFixBean(calculatorBeanMock);
    }

    @Test
    public void testCalculate() throws CalculatorException {
        assertThat(bean.handleSum("10 10 +")).isEqualTo(20.0);
    }
}
