package com.jbajak.treasurehunt.model
/**
 * Creates {@link TreasureHuntResult} instances.
 */
class TreasureHuntResultFactory {
    /**
     * Factory method for successful search.
     * @param visitedCells cells visited during search
     */
    static createSuccess(List<Tuple2<Integer, Integer>> visitedCells) {
        return new TreasureHuntResult(true, visitedCells);
    }

    /**
     * Factory method for hunt failed result.
     */
    static createNotFound() {
        return new TreasureHuntResult(false);
    }

}
