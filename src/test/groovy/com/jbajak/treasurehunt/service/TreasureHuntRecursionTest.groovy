package com.jbajak.treasurehunt.service

import com.jbajak.treasurehunt.model.TreasureHuntResult


class TreasureHuntRecursionTest extends GroovyTestCase {
    void testFindsResult() {
        //given
        def input = [
                [55, 14, 25, 52, 21],
                [44, 31, 11, 53, 43],
                [24, 13, 45, 12, 34],
                [42, 22, 43, 32, 41],
                [51, 23, 33, 54, 15],
        ]
        def output = [
                [1, 1],
                [5, 5],
                [1, 5],
                [2, 1],
                [4, 4],
                [3, 2],
                [1, 3],
                [2, 5],
                [4, 3]
        ];
        def th = new TreasureHuntRecursion();

        //when
        TreasureHuntResult result = th.findTreasure(input as int[][]);

        //then
        assertTrue(result.treasureFound);
        assertEquals(output, result.visitedCells);
    }

    void testFindsResult2() {
        //given
        int[][] input = [
                [55, 14, 25, 52, 21],
                [44, 31, 11, 53, 43],
                [24, 13, 45, 12, 34],
                [42, 22, 43, 32, 41],
                [51, 23, 33, 54, 55],
        ]
        def output = [
                [1, 1],
                [5, 5]
        ];
        def th = new TreasureHuntRecursion();

        //when
        TreasureHuntResult result = th.findTreasure(input);

        //then
        assertTrue(result.treasureFound);
        assertEquals(output, result.visitedCells);
    }

    void testNoTreasury1() {
        //given
        def input = [
                [55, 14, 25, 52, 21],
                [44, 31, 11, 53, 43],
                [24, 13, 45, 12, 34],
                [42, 22, 43, 32, 41],
                [51, 23, 33, 54, 15],
        ]
        def th = new TreasureHuntRecursion();

        //when
        TreasureHuntResult result = th.findTreasure(input as int[][], 4, 2);

        //then
        assertFalse(result.treasureFound);
    }

    void testNoTreasury2() {
        //given
        def input = [
                [55, 14, 25, 52, 21],
                [44, 31, 11, 53, 43],
                [24, 13, 45, 12, 34],
                [42, 22, 44, 32, 41],
                [51, 23, 33, 54, 15],
        ]
        def th = new TreasureHuntRecursion();

        //when
        TreasureHuntResult result = th.findTreasure(input as int[][]);

        //then
        assertFalse(result.treasureFound);
    }

    void testThrowsExceptionForNegativeStartX() {
        //given
        def input = [
                [55, 14, 25, 52, 21],
                [44, 31, 11, 53, 43],
                [24, 13, 45, 12, 34],
                [42, 22, 43, 32, 41],
                [51, 23, 33, 54, 55],
        ]
        def output = [
                [1, 1],
                [5, 5]
        ];
        def th = new TreasureHuntRecursion();

        def msg = shouldFail(IllegalStateException) {
            //when
            th.findTreasure(input as int[][], -1, 3);
        }

        //then
        assertEquals("Start cell X parameter must be > 0", msg);
    }

    void testThrowsExceptionForNegativeStartY() {
        //given
        def input = [
                [55, 14, 25, 52, 21],
                [44, 31, 11, 53, 43],
                [24, 13, 45, 12, 34],
                [42, 22, 43, 32, 41],
                [51, 23, 33, 54, 55],
        ]
        def output = [
                [1, 1],
                [5, 5]
        ];
        def th = new TreasureHuntRecursion();

        def msg = shouldFail(IllegalStateException) {
            //when
            th.findTreasure(input as int[][], 1, -3);
        }

        //then
        assertEquals("Start cell Y parameter must be > 0", msg);
    }

    void testThrowsExceptionForNullInputArray() {
        //given
        def input = null;
        def output = [
                [1, 1],
                [5, 5],
                [1, 5],
                [2, 1],
                [4, 4],
                [3, 2],
                [1, 3],
                [2, 5],
                [4, 3]
        ];

        def service = new TreasureHuntRecursion();

        def msg = shouldFail(IllegalStateException) {
            //when
            service.findTreasure(input as int[][])
        }

        //then
        assertEquals("Input array can not be null or empty", msg);
    }

    void testThrowsExceptionForEmptyInputArray() {
        //given
        def input = new int[0][0];
        def output = [
                [1, 1],
                [5, 5],
                [1, 5],
                [2, 1],
                [4, 4],
                [3, 2],
                [1, 3],
                [2, 5],
                [4, 3]
        ];

        def service = new TreasureHuntRecursion();

        def msg = shouldFail(IllegalStateException) {
            //when
            service.findTreasure(input as int[][])
        }

        //then
        assertEquals("Input array can not be null or empty", msg);
    }
}
