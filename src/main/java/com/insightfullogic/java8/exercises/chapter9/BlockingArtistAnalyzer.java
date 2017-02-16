package com.insightfullogic.java8.exercises.chapter9;

import com.insightfullogic.java8.examples.chapter1.Artist;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlockingArtistAnalyzer implements ArtistAnalyzer {

    private final Function<String, Artist> artistLookupService;

    public BlockingArtistAnalyzer(Function<String, Artist> artistLookupService) {
        this.artistLookupService = artistLookupService;
    }


    private CompletableFuture<Long> getNumberOfMembers(String artistName) {
        long count = artistLookupService.apply(artistName)
                .getMembers()
                .count();
        CompletableFuture<Long> booleanCompletableFuture = new CompletableFuture<>();
        booleanCompletableFuture.complete(count);
        return booleanCompletableFuture;
    }

    @Override
    public void isLargerGroup(String artistName, String otherArtistName, Consumer<Boolean> handler) {
        CompletableFuture<Long> numberOfMembersArtistName = getNumberOfMembers(artistName);
        CompletableFuture<Long> numberOfMembersOtherArtistName = getNumberOfMembers(otherArtistName);
        numberOfMembersArtistName.thenAcceptBoth(numberOfMembersOtherArtistName, (artistResp, otherArtistResp) -> {
            boolean result = artistResp > otherArtistResp;
            handler.accept(result);
        });
    }
}
