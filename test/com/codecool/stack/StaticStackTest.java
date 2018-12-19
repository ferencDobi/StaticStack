package com.codecool.stack;

import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class StaticStackTest {

    private StaticStack<Integer> stack;
    private final Integer[] items = new Integer[] { 15, 7 };

    @BeforeEach
    void setUp() {
        stack = new StaticStack<>(5);
        Arrays.stream(items).forEach(stack::push);
    }

    @TestFactory
    DynamicTest[] constructor() {
        String errorMessage = "Stack capacity must be 1 or greater.";
        return new DynamicTest[] {
                dynamicTest("Instantiating a valid stack doesn't throw exceptions",
                        () -> assertDoesNotThrow(() -> new StaticStack<>(1))),
                dynamicTest("Trying to instantiate a zero-capacity stack throws an exception",
                        () -> assertThrows(IllegalArgumentException.class,
                                () -> new StaticStack<String>(0), errorMessage)),
                dynamicTest("Trying to instantiate a negative-capacity stack throws an exception",
                        () -> assertThrows(IllegalArgumentException.class,
                                () -> new StaticStack<String>(-1), errorMessage))
        };
    }

    @TestFactory
    DynamicTest[] push() {
        Integer item = 5;
        return new DynamicTest[] {
                dynamicTest("Pushing an item to the stack places it on the top.", () -> {
                    stack.push(item);
                    assertEquals(item, stack.peek());
                    assertEquals(item, stack.pop());
                }),
                dynamicTest("Pushing an item onto a full stack throws an exception.", () -> {
                    while (!stack.isFull()) stack.push(item);
                    assertThrows(StackOverflow.class, () -> stack.push(item));
                })
        };
    }

    @TestFactory
    DynamicTest[] pop() {
        return new DynamicTest[] {
                dynamicTest("Popping an item from the stack returns said item.",
                        () -> assertEquals(items[items.length - 1], stack.pop())),
                dynamicTest("Popping an item from the stack removes said item.",
                        () -> assertNotEquals(items[items.length - 1], stack.pop())),
                dynamicTest("Popping an item from an empty stack throws an exception.", () -> {
                    while (!stack.isEmpty()) stack.pop();
                    assertThrows(StackUnderflow.class, () -> stack.pop());
                })
        };
    }

    @TestFactory
    DynamicTest[] peek() {
        return new DynamicTest[] {
                dynamicTest("Peeking at a stack return the item on top.",
                        () -> assertEquals(items[items.length - 1], stack.peek())),
                dynamicTest("Peeking at a stack does not remove the item on top.",
                        () -> assertEquals(items[items.length - 1], stack.peek())),
                dynamicTest("Peeking at an empty stack throws an exception.",
                        () -> assertThrows(StackUnderflow.class, () -> new StaticStack<Integer>(15).peek()))
        };
    }

    @TestFactory
    DynamicTest[] size() {
        return new DynamicTest[] {
                dynamicTest("Size returns the correct size of the stack.",
                        () -> assertEquals(items.length, stack.size())),
                dynamicTest("Pushing an item to the stack changes its size.", () -> {
                    assertEquals(items.length, stack.size());
                    stack.push(5);
                    assertEquals(items.length + 1, stack.size());
                }),
                dynamicTest("Popping an item from the stack changes its size.", () -> {
                    assertEquals(items.length + 1, stack.size());
                    stack.pop();
                    assertEquals(items.length, stack.size());
                }),
                dynamicTest("Peeking at the stack doesn't change its size.", () -> {
                    assertEquals(items.length, stack.size());
                    stack.peek();
                    assertEquals(items.length, stack.size());
                }),
                dynamicTest("An empty stack returns with 0 size.",
                        () -> assertEquals(0, new StaticStack<Integer>(15).size())),
                dynamicTest("A cleared stack return with 0 size.", () -> {
                    stack.push(5);
                    stack.clear();
                    assertEquals(0, stack.size());
                })
        };
    }

    @DisplayName("clear(): Clear removes all items from the stack.")
    @Test
    void clear() {
        assertTrue(0 < stack.size()); // Sanity check for proper before each setup.
        stack.clear();
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        assertThrows(StackUnderflow.class, () -> stack.peek());
    }

    @TestFactory
    DynamicTest[] isEmpty() {
        return new DynamicTest[] {
                dynamicTest("When instantiating an empty stack, isEmpty returns true.",
                        () -> assertTrue(new StaticStack<Integer>(10).isEmpty())),
                dynamicTest("When querying a non-empty stack, isEmpty returns false.",
                        () -> assertFalse(stack.isEmpty()))
        };
    }

    @TestFactory
    DynamicTest[] isFull() {
        StaticStack<String> smallStack = new StaticStack<>(2);
        return new DynamicTest[] {
                dynamicTest("When instantiating an empty stack, isFull returns false.",
                        () -> assertFalse(smallStack.isFull())),
                dynamicTest("When space is available on a non-empty stack, isFull returns false.", () -> {
                    smallStack.push("item 1");
                    assertFalse(smallStack.isFull());
                }),
                dynamicTest("When no space remains on a stack, isFull returns true.", () -> {
                    smallStack.push("last item");
                    assertTrue(smallStack.isFull());
                })
        };
    }
}