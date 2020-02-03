package com.jbajak.treasurehunt.service


import com.jbajak.treasurehunt.model.InvalidParameterValueException

class InputParamParserTest extends GroovyTestCase {

    void testParseAllValidValues() {
        //given
        for (int x = 1; x <= 5; x++) {
            for (int y = 1; y <= 5; y++) {
                //when
                def input = "" + x + y
                def result = InputParamParser.tryParse(input)

                //then
                assertEquals(x, result.getFirst());
                assertEquals(y, result.getSecond());
            }
        }
    }

    void testParseValidValues1() {
        //given
        def input = "34"

        //when
        def result = InputParamParser.tryParse(input)

        //then
        assertEquals(3, result.getFirst());
        assertEquals(4, result.getSecond());
    }

    void testParseValidValues2() {
        //given
        def input = "55"

        //when
        def result = InputParamParser.tryParse(input)

        //then
        assertEquals(5, result.getFirst());
        assertEquals(5, result.getSecond());
    }

    void testParseValidValues3() {
        //given
        def input = "21"

        //when
        def result = InputParamParser.tryParse(input)

        //then
        assertEquals(2, result.getFirst());
        assertEquals(1, result.getSecond());
    }

    void testParseValidValues4() {
        //given
        def input = "42"

        //when
        def result = InputParamParser.tryParse(input)

        //then
        assertEquals(4, result.getFirst());
        assertEquals(2, result.getSecond());
    }

    void testThrowsExceptionForLetterAsX() {
        //given
        def input = "a2"

        def msg = shouldFail(InvalidParameterValueException) {
            //when
            InputParamParser.tryParse(input)
        }

        //then
        assertEquals("Start parameter X must be a number.", msg);
    }

    void testThrowsExceptionForLetterAsY() {
        //given
        def input = "4a"

        def msg = shouldFail(InvalidParameterValueException) {
            //when
            InputParamParser.tryParse(input)
        }

        //then
        assertEquals("Start parameter Y must be a number.", msg);
    }

    void testThrowsExceptionForTooLowX() {
        //given
        def input = "02"

        def msg = shouldFail(InvalidParameterValueException) {
            //when
            InputParamParser.tryParse(input)
        }

        //then
        assertEquals("Start parameter X must meet condition 0 < x < 5", msg);
    }

    void testThrowsExceptionForTooLowY() {
        //given
        def input = "40"

        def msg = shouldFail(InvalidParameterValueException) {
            //when
            InputParamParser.tryParse(input)
        }

        //then
        assertEquals("Start parameter Y must meet condition 0 < y < 5", msg);
    }

    void testThrowsExceptionForTooHighX() {
        //given
        def input = "72"

        def msg = shouldFail(InvalidParameterValueException) {
            //when
            InputParamParser.tryParse(input)
        }

        //then
        assertEquals("Start parameter X must meet condition 0 < x < 5", msg);
    }

    void testThrowsExceptionForTooHighY() {
        //given
        def input = "47"

        def msg = shouldFail(InvalidParameterValueException) {
            //when
            InputParamParser.tryParse(input)
        }

        //then
        assertEquals("Start parameter Y must meet condition 0 < y < 5", msg);
    }

    void testThrowsExceptionForTooLongInput() {
        //given
        def input = "4734"

        def msg = shouldFail(InvalidParameterValueException) {
            //when
            InputParamParser.tryParse(input)
        }

        //then
        assertEquals("Too long value, start parameter must be 2 digits long.", msg);
    }

    void testThrowsExceptionForTooShortInput() {
        //given
        def input = "4"

        def msg = shouldFail(InvalidParameterValueException) {
            //when
            InputParamParser.tryParse(input)
        }

        //then
        assertEquals("Too short value, start parameter must be 2 digits long.", msg);
    }

    void testThrowsExceptionForNullInput() {
        //given
        def input = null

        def msg = shouldFail(InvalidParameterValueException) {
            //when
            InputParamParser.tryParse(input)
        }

        //then
        assertEquals("Empty value is not correct, start parameter must be 2 digits long.", msg);
    }

}
