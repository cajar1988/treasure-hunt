package com.jbajak.treasurehunt.service

import com.jbajak.treasurehunt.model.InvalidParameterValueException

/**
 * Responsible for mapping HTTP/URL based values into objects
 */
class InputParamParser {
    /**
     * Tries to convert string based param value into Tuple of X,Y coordinates.
     * @param start input string
     * @return Pair of digits - X,Y coordinates
     * @throws InvalidParameterValueException when any problem occurs
     */
    static Tuple2<Integer, Integer> tryParse(String start) throws InvalidParameterValueException {
        if (start == null) {
            throw new InvalidParameterValueException("Empty value is not correct, start parameter must be 2 digits long.")
        } else if (start.length() < 2) {
            throw new InvalidParameterValueException("Too short value, start parameter must be 2 digits long.")
        } else if (start.length() > 2) {
            throw new InvalidParameterValueException("Too long value, start parameter must be 2 digits long.")
        }
        def x;
        try {
            x = Integer.parseInt(start.charAt(0).toString())
        } catch (NumberFormatException e) {
            throw new InvalidParameterValueException("Start parameter X must be a number.")
        }

        def y;
        try {
            y = Integer.parseInt(start.charAt(1).toString())
        } catch (NumberFormatException e) {
            throw new InvalidParameterValueException("Start parameter Y must be a number.")
        }

        if (x <= 0 || x > 5) {
            throw new InvalidParameterValueException("Start parameter X must meet condition 0 < x < 5")
        }
        if (y <= 0 || y > 5) {
            throw new InvalidParameterValueException("Start parameter Y must meet condition 0 < y < 5")
        }

        return new Tuple2<Integer, Integer>(x, y);
    }

}
