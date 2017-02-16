package com.insightfullogic.java8.exercises.chapter6;

import com.insightfullogic.java8.exercises.Exercises;

import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

public class SerialToParallel {

    public static int sumOfSquares(IntStream range) {
        return range.parallel()
                .mapToObj(x -> x * x)
                .reduce(0, (acc, x) -> acc + x, (acc1, acc2) -> acc1 + acc2);
    }

    public static int sequentialSumOfSquares(IntStream range) {
        return Exercises.replaceThisWithSolution();
    }

}
