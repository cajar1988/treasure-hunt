package com.jbajak.treasurehunt.service

import com.jbajak.treasurehunt.model.TreasureHuntResult
import com.jbajak.treasurehunt.model.TreasureHuntResultFactory

/**
 * Hunts for treasure without recursion.
 */
class TreasureHuntNoRecursion extends AbstractTreasureHunt {

    TreasureHuntNoRecursion() {
    }

    TreasureHuntResult findTreasure(final int[][] input, final int startX = 0, final int startY = 0) {
        if (input == null || input.length == 0) {
            throw new IllegalStateException("Input array can not be null or empty");
        }

        if (startX < 0) {
            throw new IllegalStateException("Start cell X parameter must be > 0")
        }
        if (startY < 0) {
            throw new IllegalStateException("Start cell Y parameter must be > 0")
        }
        List<Tuple2<Integer, Integer>> visited = new ArrayList<>();
        boolean canContinue = true;
        int x = startX;
        int y = startY;
        while (canContinue) {
            def value = input[x][y];
            def currentCell = new Tuple2(x + 1, y + 1)
            visited.add(currentCell);
            def nextCellValue = value.toString();
            if (nextCellValue == "" + (x + 1) + (y + 1)) {
                return TreasureHuntResultFactory.createSuccess(visited);
            } else {
                x = Integer.parseInt(nextCellValue.charAt(0).toString()) - 1;
                y = Integer.parseInt(nextCellValue.charAt(1).toString()) - 1;
                def nextCell = new Tuple2(x + 1, y + 1)
                canContinue = !visited.contains(nextCell);
            }
        }
        return TreasureHuntResultFactory.createNotFound();
    }


}
