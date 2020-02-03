package com.jbajak.treasurehunt.model

import groovy.transform.PackageScope

/**
 * Represents Treasure Hunt result.
 * Use {@link TreasureHuntResultFactory} to create instances of this class.
 */
class TreasureHuntResult {
    private final boolean treasureFound;
    private final List<Tuple2<Integer, Integer>> visitedCells;

    @PackageScope
    TreasureHuntResult(boolean treasureFound, List<Tuple2<Integer, Integer>> visitedCells) {
        if (visitedCells == null) {
            throw new IllegalStateException("Visited cells list can not be null");
        }
        this.treasureFound = treasureFound
        this.visitedCells = visitedCells
    }

    @PackageScope
    TreasureHuntResult(boolean treasureFound) {
        this(treasureFound, new ArrayList<Tuple2<Integer, Integer>>());
    }

    boolean getTreasureFound() {
        return treasureFound
    }

    List<Tuple2<Integer, Integer>> getVisitedCells() {
        return visitedCells
    }
}
