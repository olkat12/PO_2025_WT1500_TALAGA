package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    // equals(Object other)

    @Test
    void equalsWhenVectorsEqualButDifferentObjects() {
        // given
        Vector2d v1 = new Vector2d(5, 8);
        Vector2d v2 = new Vector2d(5, 8);

        //then
        assertEquals(v1.equals(v2), true);
    }

    @Test
    void equalsWhenVectorsAreTheSameObjects() {
        // given
        Vector2d v1 = new Vector2d(5, 8);
        Vector2d v2 = v1;

        //then
        assertTrue(v1.equals(v2));
    }

    @Test
    void equalsWhenDidderentObjects() {
        // given
        Vector2d v1 = new Vector2d(5, 8);
        Vector2d v2 = new Vector2d(2, 4);

        //then
        assertFalse(v1.equals(v2));
    }

    // toString()

    @Test
    void standardToStringTest() {
        // given
        Vector2d v1 = new Vector2d(5, 8);
        String expectedOutput = "(5, 8)";

        //then
        assertEquals(v1.toString(), expectedOutput);
    }


    // precedes(Vector2d other)

    @Test
    void precedesWhenTrue() {
        // given
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(5, 2);

        //then
        assertTrue(v1.precedes(v2));
    }


    @Test
    void precedesWhenFalse() {
        // given
        Vector2d v1 = new Vector2d(5, 2);
        Vector2d v2 = new Vector2d(5, 1);

        //then
        assertFalse(v1.precedes(v2));
    }

    // follows(Vector2d other)

    @Test
    void followsWhenTrue() {
        // given
        Vector2d v1 = new Vector2d(5, 4);
        Vector2d v2 = new Vector2d(1, 2);

        //then
        assertTrue(v1.follows(v2));
    }


    @Test
    void followsWhenFalse() {
        // given
        Vector2d v1 = new Vector2d(5, 2);
        Vector2d v2 = new Vector2d(10, 11);

        //then
        assertFalse(v1.follows(v2));
    }

    // upperRigth(Vector2d other)

    @Test
    void TwoVectorsUpperRight() {
        //given
        Vector2d v1 = new Vector2d(1,5);
        Vector2d v2 = new Vector2d(4,2);

        //when
        Vector2d upperRight = v1.upperRight(v2);
        Vector2d expectedVector = new Vector2d( 4,5);

        //then
        assertEquals(upperRight, expectedVector);
    }

    // lowerLeft(Vector2d other)

    @Test
    void TwoVectorsLowerLeft() {
        //given
        Vector2d v1 = new Vector2d(1,5);
        Vector2d v2 = new Vector2d(4,2);

        //when
        Vector2d lowerLeft = v1.lowerLeft(v2);
        Vector2d expectedVector = new Vector2d( 1,2);

        //then
        assertEquals(lowerLeft, expectedVector);
    }

    // add(Vector2d other)

    @Test
    void addTwoVectors() {
        //given
        Vector2d v1 = new Vector2d(1,5);
        Vector2d v2 = new Vector2d(4,2);

        //when
        Vector2d addVector = v1.add(v2);
        Vector2d expectedVector = new Vector2d( 5,7);

        //then
        assertEquals(addVector, expectedVector);
    }

    // substract(Vector2d other)

    @Test
    void substractTwoVectors() {
        //given
        Vector2d v1 = new Vector2d(1,5);
        Vector2d v2 = new Vector2d(4,2);

        //when
        Vector2d substractVector = v1.subtract(v2);
        Vector2d expectedVector = new Vector2d( -3,3);

        //then
        assertEquals(substractVector, expectedVector);
    }

    // opposite

    @Test
    void oppositeVector() {
        //given
        Vector2d v1 = new Vector2d(1,5);

        //when
        Vector2d opposite = v1.opposite();
        Vector2d expectedVector = new Vector2d(-1, -5);

        //then
        assertEquals(opposite, expectedVector);
    }

    @Test
    void oppositeVectorWhenZeros() {
        //given
        Vector2d v1 = new Vector2d(0,0);

        //when
        Vector2d opposite = v1.opposite();
        Vector2d expectedVector = new Vector2d(0, 0);

        //then
        assertEquals(opposite, expectedVector);
    }

}