/*
 * Copyright 2017 original authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package com.jbajak.treasurehunt.controller

import com.jbajak.treasurehunt.model.InvalidParameterValueException
import com.jbajak.treasurehunt.service.AbstractTreasureHunt
import com.jbajak.treasurehunt.service.InputParamParser
import com.jbajak.treasurehunt.service.TreasureHuntNoRecursion
import com.jbajak.treasurehunt.service.TreasureHuntRecursion
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/treasure/")
class TreasureHuntController {
    /**
     * Returns array with Treasure Hunt input values.
     */
    private static int[][] getInputArray() {
        return [
                [55, 14, 25, 52, 21],
                [44, 31, 11, 53, 43],
                [24, 13, 45, 12, 34],
                [42, 22, 43, 32, 41],
                [51, 23, 33, 54, 15],
        ]
    }

    @Get(uri = "/recursive/{start}")
    HttpResponse findRecursive(String start) {
        find(start, new TreasureHuntRecursion())
    }

    @Get(uri = "/notrecursive/{start}")
    HttpResponse findNotRecursive(String start) {
        find(start, new TreasureHuntNoRecursion())
    }

    private static HttpResponse find(String start, AbstractTreasureHunt service) {
        //read input array
        def input = getInputArray()
        def startPos
        try {
            startPos = InputParamParser.tryParse(start);
        } catch (InvalidParameterValueException e) {
            return HttpResponse.serverError(e.getMessage());
        }

        def x = startPos.getFirst();
        def y = startPos.getSecond();

        //calculate treasure
        def result = service.findTreasure(input, x - 1, y - 1)

        //convert result
        def jsonResult = new TreasureHuntResultJson(result)
        return HttpResponse.ok(jsonResult);
    }
}
