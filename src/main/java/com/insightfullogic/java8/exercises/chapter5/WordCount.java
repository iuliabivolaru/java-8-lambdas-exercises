package com.insightfullogic.java8.exercises.chapter5;

import com.insightfullogic.java8.exercises.Exercises;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class WordCount {

    public static Map<String, Long> countWords(Stream<String> names) {
        BiFunction<HashMap<String, Long>, String, HashMap<String, Long>> accumulator = (previousMap, name) -> {
            HashMap<String, Long> newMap = new HashMap<>();
            newMap.putAll(previousMap);
            newMap.put(name, newMap.getOrDefault(name, 0L) + 1);
            return newMap;
        };
        BinaryOperator<HashMap<String, Long>> combiner = (map1, map2) -> {
            HashMap<String, Long> result = new HashMap<>();
            result.putAll(map1);
            map2.keySet().forEach(
                    name -> result.put(name, result.getOrDefault(name, 0L) + map2.get(name)));
            return result;
        };
        return names.reduce(new HashMap<>(), accumulator, combiner);
    }

}
