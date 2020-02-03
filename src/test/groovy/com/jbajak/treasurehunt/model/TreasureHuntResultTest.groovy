package com.jbajak.treasurehunt.model

class TreasureHuntResultTest extends GroovyTestCase {
    void testCreatesResultForNoTreasure() {
        //when
        def result = TreasureHuntResultFactory.createNotFound()

        //then
        assertFalse(result.treasureFound)
        assertNotNull(result.visitedCells)
        assertEquals(0,result.visitedCells.size())
    }

    void testCreatesResultForTreasureFound() {
        //given
        List<Tuple2<Integer, Integer>> visited = new ArrayList<>();
        visited.add(new Tuple2(1, 1))
        visited.add(new Tuple2(2, 5))
        visited.add(new Tuple2(4, 3))

        //when
        def result = TreasureHuntResultFactory.createSuccess(visited)

        //then
        assertTrue(result.treasureFound)
        assertEquals(3, result.getVisitedCells().size());
        assertEquals(new Tuple2(1, 1), result.getVisitedCells().get(0));
        assertEquals(new Tuple2(2, 5),  result.getVisitedCells().get(1));
        assertEquals(new Tuple2(4, 3),  result.getVisitedCells().get(2));
    }

    void testThrowsExceptionForNull() {
        //given
        def result = null

        def msg = shouldFail(IllegalStateException) {
            //when
            TreasureHuntResultFactory.createSuccess(result)
        }

        //then
        assertEquals("Visited cells list can not be null", msg);
    }

}
