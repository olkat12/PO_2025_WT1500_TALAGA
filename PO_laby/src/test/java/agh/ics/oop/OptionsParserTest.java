package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {


    @Test
    void parseAllDirections() {

        // given
        String argsF[] = {"f"};
        List<MoveDirection> expectedDirectionsF = new ArrayList<>();
        expectedDirectionsF.add(MoveDirection.FORWARD);


        String argsB[] = {"b"};
        List<MoveDirection> expectedDirectionsB = new ArrayList<>();
        expectedDirectionsB.add(MoveDirection.BACKWARD);

        String argsR[] = {"r"};
        List<MoveDirection> expectedDirectionsR = new ArrayList<>();
        expectedDirectionsR.add(MoveDirection.RIGHT);

        String argsL[] = {"l"};
        List<MoveDirection> expectedDirectionsL = new ArrayList<>();
        expectedDirectionsL.add(MoveDirection.LEFT);

        //when
        List<MoveDirection> directionsF = OptionsParser.parse(argsF);
        List<MoveDirection> directionsB = OptionsParser.parse(argsB);
        List<MoveDirection> directionsR = OptionsParser.parse(argsR);
        List<MoveDirection> directionsL = OptionsParser.parse(argsL);

        //then
        assertEquals(expectedDirectionsF, directionsF);
        assertEquals(expectedDirectionsB, directionsB);
        assertEquals(expectedDirectionsR, directionsR);
        assertEquals(expectedDirectionsL, directionsL);
    }

    @Test
    void parseMultipleRightSymbols() {
        // given
        String args[] = {"f", "b", "r", "r", "l"};
        List<MoveDirection> expectedDirections = List.of(MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.LEFT);

        //when
        List<MoveDirection> directions = OptionsParser.parse(args);

        //then
        assertEquals(expectedDirections, directions);
    }

    @Test
    void parseAllWrongSymbolsShouldBeEmpty() {
        // given
        String args[] = {"g", "t", "234"};
        List<MoveDirection> expectedDirections = new ArrayList<>();

        //when
        List<MoveDirection> directions = OptionsParser.parse(args);

        //then
        assertEquals(expectedDirections, directions);
    }


    @Test
    void parseMultipleSymbolsAndShouldSkipWrongSymbols() {
        // given
        String args[] = {"f", "b", "123", "ola", "l"};
        List<MoveDirection> expectedDirections = List.of(MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.LEFT);

        //when
        List<MoveDirection> directions = OptionsParser.parse(args);

        //then
        assertEquals(expectedDirections, directions);
    }

    @Test
    void parseEmptyArray() {
        //given
        String args[] = {};
        List<MoveDirection> expectedDirections = new ArrayList<>();

        //when
        List<MoveDirection> directions = OptionsParser.parse(args);

        //then
        assertEquals(expectedDirections, directions);
    }

}