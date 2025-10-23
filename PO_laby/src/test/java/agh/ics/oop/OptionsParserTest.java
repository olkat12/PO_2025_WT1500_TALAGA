package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parseForward()
    {
        // given
        String args[] = {"f"};
        MoveDirection[] expectedDirections = {MoveDirection.FORWARD};

        //when
        MoveDirection[] directions = OptionsParser.parse(args);

        //then
        assertArrayEquals(directions, expectedDirections);
    }

    @Test
    void parseBackward()
    {
        // given
        String args[] = {"b"};
        MoveDirection[] expectedDirections = {MoveDirection.BACKWARD};

        //when
        MoveDirection[] directions = OptionsParser.parse(args);

        //then
        assertArrayEquals(directions, expectedDirections);
    }

    @Test
    void parseRight()
    {
        // given
        String args[] = {"r"};
        MoveDirection[] expectedDirections = {MoveDirection.RIGHT};

        //when
        MoveDirection[] directions = OptionsParser.parse(args);

        //then
        assertArrayEquals(directions, expectedDirections);
    }

    @Test
    void parseLeft()
    {
        // given
        String args[] = {"l"};
        MoveDirection[] expectedDirections = {MoveDirection.LEFT};

        //when
        MoveDirection[] directions = OptionsParser.parse(args);

        //then
        assertArrayEquals(directions, expectedDirections);
    }

    @Test
    void parseMultipleRightSymbols()
    {
        // given
        String args[] = {"f", "b", "r", "r", "l"};
        MoveDirection[] expectedDirections = {MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.LEFT};

        //when
        MoveDirection[] directions = OptionsParser.parse(args);

        //then
        assertArrayEquals(directions, expectedDirections);
    }

    @Test
    void parseAllWrongSymbolsShouldBeEmpty()
    {
        // given
        String args[] = {"g", "t", "234"};
        MoveDirection[] expectedDirections = {};

        //when
        MoveDirection[] directions = OptionsParser.parse(args);

        //then
        assertArrayEquals(directions, expectedDirections);
    }


    @Test
    void parseMultipleRightSymbolsAndShouldSkipWrongSymbols()
    {
        // given
        String args[] = {"f", "b", "123", "ola", "l"};
        MoveDirection[] expectedDirections = {MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.LEFT};

        //when
        MoveDirection[] directions = OptionsParser.parse(args);

        //then
        assertArrayEquals(directions, expectedDirections);
    }

}