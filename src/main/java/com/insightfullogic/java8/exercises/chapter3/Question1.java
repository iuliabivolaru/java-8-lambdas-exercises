package com.insightfullogic.java8.exercises.chapter3;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Question1 {
    public static int addUp(Stream<Integer> numbers) {
        return numbers.reduce(0, (x,y)-> x+y);
    }

    public static List<String> getNamesAndOrigins(List<Artist> artists) {
        return artists.stream().flatMap(getArtistStreamFunction()).collect(toList());
    }

    private static Function<Artist, Stream<String>> getArtistStreamFunction() {
        return artist -> getNameAndNationality(artist);
    }

    private static Stream<String> getNameAndNationality(Artist artist) {
        return Stream.of(artist.getName(),artist.getNationality());
    }

    public static List<Album> getAlbumsWithAtMostThreeTracks(List<Album> input) {
        return input.stream().filter(album -> album.getTrackList().size() <= 3).collect(toList());
    }
}
