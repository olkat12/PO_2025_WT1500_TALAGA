package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {


    @Test
    void parseAllDirections() {

        // given
        String argsF[] = {"f"};
        MoveDirection[] expectedDirectionsF = {MoveDirection.FORWARD};

        String argsB[] = {"b"};
        MoveDirection[] expectedDirectionsB = {MoveDirection.BACKWARD};

        String argsR[] = {"r"};
        MoveDirection[] expectedDirectionsR = {MoveDirection.RIGHT};

        String argsL[] = {"l"};
        MoveDirection[] expectedDirectionsL = {MoveDirection.LEFT};

        //when
        MoveDirection[] directionsF = OptionsParser.parse(argsF);
        MoveDirection[] directionsB = OptionsParser.parse(argsB);
        MoveDirection[] directionsR = OptionsParser.parse(argsR);
        MoveDirection[] directionsL = OptionsParser.parse(argsL);

        //then
        assertArrayEquals(expectedDirectionsF, directionsF);
        assertArrayEquals(expectedDirectionsB, directionsB);
        assertArrayEquals(expectedDirectionsR, directionsR);
        assertArrayEquals(expectedDirectionsL, directionsL);
    }

    @Test
    void parseMultipleRightSymbols() {
        // given
        String args[] = {"f", "b", "r", "r", "l"};
        MoveDirection[] expectedDirections = {MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.LEFT};

        //when
        MoveDirection[] directions = OptionsParser.parse(args);

        //then
        assertArrayEquals(expectedDirections, directions);
    }

    @Test
    void parseAllWrongSymbolsShouldBeEmpty() {
        // given
        String args[] = {"g", "t", "234"};
        MoveDirection[] expectedDirections = {};

        //when
        MoveDirection[] directions = OptionsParser.parse(args);

        //then
        assertArrayEquals(expectedDirections, directions);
    }


    @Test
    void parseMultipleSymbolsAndShouldSkipWrongSymbols() {
        // given
        String args[] = {"f", "b", "123", "ola", "l"};
        MoveDirection[] expectedDirections = {MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.LEFT};

        //when
        MoveDirection[] directions = OptionsParser.parse(args);

        //then
        assertArrayEquals(expectedDirections, directions);
    }

    @Test
    void parseEmptyArray() {
        //given
        String args[] = {};
        MoveDirection[] expectedDirections = {};

        //when
        MoveDirection[] directions = OptionsParser.parse(args);

        //then
        assertArrayEquals(expectedDirections, directions);
    }

}