package com.codecool.stack;

public class StackOverflow extends RuntimeException {
    public StackOverflow() {
        super("Can't push element to full stack.");
    }

    public StackOverflow(String errorMessage) {
        super(errorMessage);
    }
}
