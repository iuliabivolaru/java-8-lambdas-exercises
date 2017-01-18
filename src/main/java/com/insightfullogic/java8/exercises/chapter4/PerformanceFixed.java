package com.insightfullogic.java8.exercises.chapter4;

import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.exercises.Exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Stream.concat;

/** A Performance by some musicians - eg an Album or Gig. */
public interface PerformanceFixed {

    public String getName();

    public Stream<Artist> getMusicians();

    public default Stream<Artist> getAllMusicians() {
        return getMusicians().flatMap(mapper2());
    }

    default Function<Artist, Stream<? extends Artist>> mapper() {
        return artist -> {
            List<Artist> listOfArtists = new ArrayList<Artist>();
            listOfArtists.add(artist);
            Stream<Artist> members = artist.getMembers();
            listOfArtists.addAll(members.collect(Collectors.toList()));
            return listOfArtists.stream();
        };
    }

    default Function<Artist, Stream<? extends Artist>> mapper2() {
        return artist -> Stream.of(Stream.of(artist), artist.getMembers()).flatMap(a->a);
    }

}
