package com.insightfullogic.java8.exercises.chapter5;

import com.insightfullogic.java8.exercises.Exercises;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    public Fibonacci() {
    }

    private HashMap<Integer, Long> stash = new HashMap();

    public long fibonacci(int x) {
        return stash.computeIfAbsent(x, (key) -> {
            switch (key) {
                case 0:
                    return 0L;
                case 1:
                    return 1L;
                default:
                    return fibonacci(key - 2) + fibonacci(key - 1);
            }
        });
    }

}
