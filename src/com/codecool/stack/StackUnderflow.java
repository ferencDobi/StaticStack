package com.codecool.stack;

public class StackUnderflow extends RuntimeException {
    public StackUnderflow() {
        super("Can't get element of empty stack.");
    }

    public StackUnderflow(String errorMessage) {
        super(errorMessage);
    }
}
