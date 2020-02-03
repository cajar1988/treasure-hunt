package com.jbajak.treasurehunt.controller

import com.jbajak.treasurehunt.model.TreasureHuntResult
import com.jbajak.treasurehunt.model.TreasureHuntResultFactory

class TreasureHuntResultJsonTest extends GroovyTestCase {
    void testCreatesResultForNoTreasure() {
        //given
        def result = TreasureHuntResultFactory.createNotFound()

        //when
        def jsonResult = new TreasureHuntResultJson(result);

        //then
        assertEquals(1, jsonResult.getMessages().size())
        assertEquals("NO TREASURE", jsonResult.getMessages().get(0));
    }

    void testCreatesResultForTreasureFound() {
        //given
        List<Tuple2<Integer, Integer>> visited = new ArrayList<>();
        visited.add(new Tuple2(1, 1))
        visited.add(new Tuple2(1, 5))
        visited.add(new Tuple2(2, 5))
        visited.add(new Tuple2(4, 3))
        def result = TreasureHuntResultFactory.createSuccess(visited)

        //when
        def jsonResult = new TreasureHuntResultJson(result);

        //then
        assertEquals(4, jsonResult.getMessages().size())
        assertEquals("1 1", jsonResult.getMessages().get(0));
        assertEquals("1 5", jsonResult.getMessages().get(1));
        assertEquals("2 5", jsonResult.getMessages().get(2));
        assertEquals("4 3", jsonResult.getMessages().get(3));
    }

    void testThrowsExceptionForNull() {
        //given
        def result = null

        def msg = shouldFail(IllegalStateException) {
            //when
            new TreasureHuntResultJson(result);
        }

        //then
        assertEquals("Input can not be null", msg);
    }

}
