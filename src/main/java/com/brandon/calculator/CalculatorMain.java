package com.brandon.calculator;

import com.brandon.calculator.beans.CommandLineBean;
import com.brandon.calculator.config.CalculatorConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CalculatorMain {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(CalculatorConfig.class);

        CommandLineBean cmd = ctx.getBean(CommandLineBean.class);
        cmd.handleCommands();
    }
}
