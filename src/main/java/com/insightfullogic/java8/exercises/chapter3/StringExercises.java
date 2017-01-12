package com.insightfullogic.java8.exercises.chapter3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringExercises {

    // Question 7
    public static int countLowercaseLetters(String string) {

        Stream<Character> characterStream =
                IntStream.rangeClosed(0, string.length() - 1)
                        .mapToObj(position ->
                                string.charAt(position));
        return (int) characterStream
                .filter(key -> Character.isLowerCase(key))
                .count();
    }

    // Question 8
    public static Optional<String> mostLowercaseString(List<String> strings) {
        return strings.stream()
                .max((str1, str2) -> new Integer(countLowercaseLetters(str1)).compareTo(countLowercaseLetters(str2)));/*


        Optional<Tuple> maxTupple = strings.stream().map(str -> new Tuple(str, countLowercaseLetters(str)))
                .reduce((t1, t2) -> t1.lowercaseLetters > t2.lowercaseLetters ? t1 : t2);
        return maxTupple.map(t -> t.element);*/
    }

    //Advanced exercise 1
    public <U, T> Stream<U> mapImplementation(Stream<T> stream, Function<T, U> function) {
        BiFunction<ArrayList<U>, ? super T, ArrayList<U>> populateList =
                (list, element) -> {
                    U elementU = function.apply(element);
                    list.add(elementU);
                    return list;
                };

        BinaryOperator<ArrayList<U>> listCombiner = (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };

        return stream.reduce(new ArrayList<U>(), populateList, listCombiner).stream();
    }

    //Advanced exercise 1 - immutable lists
    public <U, T> Stream<U> mapImplementationImmutable(Stream<T> stream, Function<T, U> function) {
        BiFunction<List<U>, ? super T, List<U>> populateList =
                (initialList, element) -> {
                    U elementU = function.apply(element);
                    ArrayList<U> finalList = new ArrayList<>(initialList);
                    finalList.add(elementU);
                    return finalList;
                };

        BinaryOperator<List<U>> listCombiner = (list1, list2) -> {
            ArrayList<U> finalList = new ArrayList<U>(list1);
            finalList.addAll(list2);
            return finalList;
        };

        List<U> identityElement = Collections.emptyList();
        return stream.reduce(identityElement, populateList, listCombiner).stream();
    }


    static class Tuple {
        String element;
        int lowercaseLetters;

        public Tuple(String element, int lowercaseLetters) {
            this.element = element;
            this.lowercaseLetters = lowercaseLetters;
        }
    }

}
