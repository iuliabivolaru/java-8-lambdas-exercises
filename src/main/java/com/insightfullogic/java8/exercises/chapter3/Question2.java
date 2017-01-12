package com.insightfullogic.java8.exercises.chapter3;

import com.insightfullogic.java8.examples.chapter1.Artist;

import java.util.List;

public class Question2 {
    // Q3
    public static int countBandMembersInternal(List<Artist> artists) {
//        return artists.stream().map(artist -> artist.getMembers().count()).reduce(0L, (x, y) -> x + y).intValue();
//        return (int)(artists.stream().mapToLong(artist -> artist.getMembers().count()).sum());
//        return (int)(artists.stream().flatMap(artist -> artist.getMembers()).count());
        return artists.stream().flatMap(artist -> artist.getMembers()).mapToInt(member -> 1).sum();
    }
}
