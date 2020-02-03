package com.jbajak.treasurehunt.controller;

import com.jbajak.treasurehunt.model.TreasureHuntResult;
import groovy.lang.Tuple2;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class TreasureHuntResultJson {
    final List<String> messages = new ArrayList<>();

    public TreasureHuntResultJson(TreasureHuntResult result) {
        if (result == null) {
            throw new IllegalStateException("Input can not be null");
        }
        if (result.getTreasureFound()) {
            List<Tuple2<Integer, Integer>> visitedCells = result.getVisitedCells();
            messages.addAll(visitedCells.stream()
                    .map(cell -> {
                        return cell.getFirst() + " " + cell.getSecond();
                    })
                    .collect(toList()));
        } else {
            messages.add("NO TREASURE");
        }
    }

    public List<String> getMessages() {
        return messages;
    }
}
