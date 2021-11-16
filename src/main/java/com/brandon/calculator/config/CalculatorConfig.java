package com.brandon.calculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Configuration
@ComponentScan("com.brandon.calculator.beans")
public class CalculatorConfig {

    @Bean
    protected BufferedReader bufferedReader(){
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
