package com.jbajak.treasurehunt.service

import com.jbajak.treasurehunt.model.TreasureHuntResult

abstract class AbstractTreasureHunt {

    /**
     * Tries to find treasure.
     */
    abstract TreasureHuntResult findTreasure(int[][] input, int startX, int startY);

}
