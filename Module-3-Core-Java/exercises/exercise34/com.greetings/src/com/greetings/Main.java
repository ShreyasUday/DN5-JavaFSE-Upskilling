package com.greetings;

import com.utils.GreetingUtils;

public class Main {

    public static void main(String[] args) {
        String message = GreetingUtils.getGreeting("There !!!");
        System.out.println(message);
    }
}