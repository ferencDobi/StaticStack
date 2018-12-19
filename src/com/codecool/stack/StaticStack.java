package com.codecool.stack;

public class StaticStack<T> {
    private T[] data;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public StaticStack(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Stack capacity must be 1 or greater.");

        data = (T[]) new Object[capacity];
    }

    public void push(T item) throws StackOverflow {
        if (isFull()) throw new StackOverflow();

        data[size++] = item;
    }

    public T pop() throws StackUnderflow {
        if (isEmpty()) throw new StackUnderflow();

        return data[--size];
    }

    public T peek() throws StackUnderflow {
        if (isEmpty()) throw new StackUnderflow();

        return data[size - 1];
    }

    public int size() {
        return size;
    }

    public void clear() {
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }
}
