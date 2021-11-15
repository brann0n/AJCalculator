package com.brandon.calculator.beans;

import com.brandon.calculator.exceptions.CalculatorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import org.mockito.ArgumentMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class CalculatorBeanTest {

    @Test
    void calculate() throws CalculatorException {
        CalculatorBean c = new CalculatorBean();
        assertThat(c.calculate(5d, 10d, '+')).isEqualTo(15d);
        assertThat(c.calculate(5d, 10d, '-')).isEqualTo(-5d);
        assertThat(c.calculate(5d, 10d, '*')).isEqualTo(50d);
        assertThat(c.calculate(5d, 10d, '/')).isEqualTo(0.5);

        assertThatThrownBy(() -> {
            c.calculate(10d, 0d, '/');
        }).isInstanceOf(CalculatorException.class).hasMessage("Cannot divide by zero");

        assertThatThrownBy(() -> {
            c.calculate(10d, 0d, '%');
        }).isInstanceOf(CalculatorException.class).hasMessage("Unknown Operator: %");
    }


}