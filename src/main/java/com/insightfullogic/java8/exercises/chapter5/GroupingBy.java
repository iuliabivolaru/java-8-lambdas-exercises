package com.insightfullogic.java8.exercises.chapter5;

import com.insightfullogic.java8.exercises.Exercises;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class GroupingBy<T, K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>> {

    private final Function<? super T, ? extends K> classifier;

    public GroupingBy(Function<? super T, ? extends K> classifier) {
        this.classifier = classifier;
    }

    @Override
    public Supplier<Map<K, List<T>>> supplier() {
        return () -> new HashMap<>();
    }

    @Override
    public BiConsumer<Map<K, List<T>>, T> accumulator() {
        return (initialMap, element) -> {
            K key = classifier.apply(element);
            List<T> values = initialMap.getOrDefault(key, new ArrayList<T>());
            values.add(element);
            initialMap.put(key, values);
        };
    }

    @Override
    public BinaryOperator<Map<K, List<T>>> combiner() {
        return (map1, map2) -> {
            Map<K, List<T>> accumulator = new HashMap<>(map1);
            map2.keySet().stream().forEach(key -> {
                List<T> values = map1.getOrDefault(key, new ArrayList<T>());
                values.addAll(map2.get(key));
                accumulator.put(key, values);
            });
            return accumulator;
        };
    }

    @Override
    public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return new HashSet() {{
            add(Characteristics.CONCURRENT);
            add(Characteristics.UNORDERED);
        }};
    }

}
