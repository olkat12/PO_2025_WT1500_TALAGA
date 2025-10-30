package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {


    @Test
    void properDefaultSettings()
    {
        //given
        MapDirection direction = MapDirection.NORTH;
        Vector2d position = new Vector2d(2,2);

        //when
        Animal animal = new Animal();

        //then
        assertEquals(direction, animal.getMapDirection());
        assertEquals(position, animal.getPosition());
    }

    @Test
    void properCreatingPosition()
    {
        //given
        Vector2d position = new Vector2d(1,1);

        //when
        Animal animal = new Animal(new Vector2d(1,1));

        //then
        assertEquals(position, animal.getPosition());
    }

    @Test
    void mapDirectionAfterMovement()
    {
        //given (2,2) North
        Animal animal = new Animal();

        //when - //then
        animal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.EAST, animal.getMapDirection());

        animal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.SOUTH, animal.getMapDirection());

        animal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.EAST, animal.getMapDirection());
    }

    @Test
    void vectorPositionAfterMovement()
    {
        //given (2,2) North
        Animal animal = new Animal();

        //when = then
        animal.move(MoveDirection.FORWARD);
        assertEquals(MapDirection.NORTH, animal.getMapDirection());
        assertEquals(new Vector2d(2,3), animal.getPosition());

        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(3,3), animal.getPosition());
        assertEquals(MapDirection.WEST, animal.getMapDirection());
    }

    @Test
    void upperBordersOfMap()
    {
        // given
        Animal animal = new Animal(new Vector2d(4,4));

        //when - then
        animal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(4,4), animal.getPosition());

        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(4,4), animal.getPosition());
    }

    @Test
    void lowerBordersOfMap()
    {
        // given
        Animal animal = new Animal(new Vector2d(0,0));

        //when - then
        animal.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(0,0), animal.getPosition());

        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(0,0), animal.getPosition());
    }

    @Test
    void isAtTrue()
    {
        // given
        Animal animal = new Animal(new Vector2d(3,0));

        // then
        assertTrue(animal.isAt(new Vector2d(3,0)));
    }

    @Test
    void isAtFalse()
    {
        // given
        Animal animal = new Animal(new Vector2d(3,0));

        // then
        assertFalse(animal.isAt(new Vector2d(2,2)));
    }

}