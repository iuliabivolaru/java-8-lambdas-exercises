package com.insightfullogic.java8.exercises.chapter3;

import com.insightfullogic.java8.exercises.Exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Advanced Exercises Question 1
 */
public class FilterUsingReduce {

    public static <I> List<I> filter(Stream<I> stream, Predicate<I> predicate) {
        BiFunction<ArrayList<I>, I, ArrayList<I>> accumulationFunction = (accumulator, currentElement) -> {
            ArrayList<I> newList = new ArrayList<I>();
            newList.addAll(accumulator);
            if(predicate.test(currentElement)) {
                newList.add(currentElement);
            }
            return newList;
        };
        BinaryOperator<ArrayList<I>> combiner = (is, is2) -> {
            ArrayList<I> newList = new ArrayList<>();
            newList.addAll(is);
            newList.addAll(is2);
            return newList;
        };
        return stream.reduce(new ArrayList<I>(), accumulationFunction, combiner);
    }

    public static <I> Stream<I> filterUsingFlatMap(Stream<I> stream, Predicate<I> predicate) {
        return stream.flatMap(element -> {
            if(predicate.test(element)) {
                return Stream.of(element);
            }
            return Stream.empty();
        });
    }

}
