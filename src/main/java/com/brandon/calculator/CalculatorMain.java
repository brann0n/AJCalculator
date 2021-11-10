package com.brandon.calculator;

import com.brandon.calculator.beans.CommandLineBean;
import com.brandon.calculator.config.CalculatorConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalculatorMain {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(CalculatorConfig.class);

        CommandLineBean cmd = ctx.getBean(CommandLineBean.class);
        cmd.handleCommands();
    }
}
