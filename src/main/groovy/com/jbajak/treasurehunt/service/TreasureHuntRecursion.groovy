package com.jbajak.treasurehunt.service

import com.jbajak.treasurehunt.model.TreasureHuntResult
import com.jbajak.treasurehunt.model.TreasureHuntResultFactory

/**
 * Hunts for treasure using recursion.
 */
class TreasureHuntRecursion extends AbstractTreasureHunt {

    TreasureHuntRecursion() {
    }

    TreasureHuntResult findTreasure(final int[][] input, int startX = 0, int startY = 0) {
        if (input == null || input.length == 0) {
            throw new IllegalStateException("Input array can not be null or empty");
        }
        return findTreasureRecursive(input, { startX }, { startY });
    }

    private TreasureHuntResult findTreasureRecursive(final int[][] input,
                                                     Closure<Integer> startX, Closure<Integer> startY,
                                                     List<Tuple2<Integer, Integer>> visited = new ArrayList<>()) {
        int x = startX.call()
        int y = startY.call()
        if (x < 0) {
            throw new IllegalStateException("Start cell X parameter must be > 0")
        }
        if (y < 0) {
            throw new IllegalStateException("Start cell Y parameter must be > 0")
        }
        def currentCell = new Tuple2(x + 1, y + 1);
        if (visited.contains(currentCell)) {
            return TreasureHuntResultFactory.createNotFound();
        }
        def value = input[x][y];
        visited.add(currentCell);
        def nextCellValue = value.toString()
        if (nextCellValue == "" + (x + 1) + (y + 1)) {
            return TreasureHuntResultFactory.createSuccess(visited);
        } else {
            def nextX = { Integer.parseInt(nextCellValue.charAt(0).toString()) - 1 }
            def nextY = { Integer.parseInt(nextCellValue.charAt(1).toString()) - 1 }
            return findTreasureRecursive(input, nextX, nextY, visited);
        }
    }


}
