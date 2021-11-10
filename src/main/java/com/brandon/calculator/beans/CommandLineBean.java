package com.brandon.calculator.beans;

import com.brandon.calculator.exceptions.CalculatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class CommandLineBean {
    private BufferedReader reader;
    private NotationBean nBean;

    @Autowired
    public CommandLineBean(NotationBean nBean) {
        reader = new BufferedReader(
                new InputStreamReader(System.in));

        this.nBean = nBean;
    }

    public void handleCommands() {
        String commandBuffer;
        System.out.println("Calculator has started.");
        while (true) {
            try {
                System.out.print("enter your sum: ");
                commandBuffer = reader.readLine();
                if (commandBuffer.equalsIgnoreCase("exit")) {

                    break;
                } else {
                    System.out.println("Answer: " + nBean.handleSum(commandBuffer));
                }
            } catch (IOException e) {
                break;
            } catch (CalculatorException e) {
                System.out.println("Something was wrong with the entered calculation: " + e.getMessage());
            }
        }
        System.out.println("Calculator has stopped.");
    }
}
