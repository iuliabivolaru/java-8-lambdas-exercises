package com.insightfullogic.java8.exercises.chapter9;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BlockingArtistAnalyzerTest {

    private final BlockingArtistAnalyzer analyser = new BlockingArtistAnalyzer(new FakeLookupService()::lookupArtistName);

    @Test
    public void largerGroupsAreLarger() {
        AtomicBoolean responseReceived = new AtomicBoolean();
        analyser.isLargerGroup("The Beatles", "John Coltrane", result -> {
            assertTrue(result);
            responseReceived.set(true);
        });
        while (!responseReceived.get()){

        }

    }

    @Test
    public void smallerGroupsArentLarger() {
        AtomicBoolean responseReceived = new AtomicBoolean();
        analyser.isLargerGroup("John Coltrane", "The Beatles", result -> {
            assertFalse(result);
            responseReceived.set(true);
        });
        while (!responseReceived.get()){

        }
    }

}
