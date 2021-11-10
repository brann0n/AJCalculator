package com.brandon.calculator.config;

import com.brandon.calculator.beans.CommandLineBean;
import com.brandon.calculator.beans.InfixNotationBean;
import com.brandon.calculator.beans.NotationBean;
import com.brandon.calculator.beans.PostFixBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan("com.brandon.calculator.beans")
public class CalculatorConfig {



}
