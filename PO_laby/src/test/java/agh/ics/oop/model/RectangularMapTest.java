package agh.ics.oop.model;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    public void objectAtWhenNotEmptyShouldReturnAnimal() {
        // given
        RectangularMap map = new RectangularMap(4,7);
        Animal animal1 = new Animal(new Vector2d(2, 4));
        map.place(animal1);

        // when then
        assertEquals(map.getElements().get(0), map.objectAt(new Vector2d(2,4)));
    }


    @Test
    public void objectAtWhenEmptyShouldReturnNull() {
        // given
        RectangularMap map = new RectangularMap(4,7);

        // when then
        assertNull(map.objectAt(new Vector2d(2,4)));
    }


    @Test
    public void isOccupiedWhenOccupiedShouldReturnTrue() {
        // given
        RectangularMap map = new RectangularMap(4,7);
        Animal animal1 = new Animal(new Vector2d(2, 4));
        map.place(animal1);

        // when then
        assertTrue(map.isOccupied(new Vector2d(2,4)));
    }


    @Test
    public void isOccupiedWhenEmptyShouldReturnFalse() {
        // given
        RectangularMap map = new RectangularMap(4,7);

        // when then
        assertFalse(map.isOccupied(new Vector2d(2,4)));
    }

    @Test
    public void canMoveToWhenTrue() {
        // given
        RectangularMap map = new RectangularMap(3,7);

        // when then
        assertTrue(map.canMoveTo(new Vector2d(3,7)));
    }


    @Test
    public void canMoveToWhenFalseBehindMapBorders() {
        // given
        RectangularMap map = new RectangularMap(3,7);

        // when then
        assertFalse(map.canMoveTo(new Vector2d(10,10)));
    }


    @Test
    public void canMoveToWhenFalseOccupiedPosition() {
        // given
        RectangularMap map = new RectangularMap(3,7);
        Animal animal1 = new Animal(new Vector2d(1,1));
        map.place(animal1);

        // when then
        assertFalse(map.canMoveTo(new Vector2d(1,1)));
    }


    @Test
    public void placeAnimalTrue(){
        // given
        RectangularMap map = new RectangularMap(3,7);
        Animal animal1 = new Animal(new Vector2d(1,1));

        // when then
        assertTrue(map.place(animal1));
        assertTrue(map.isOccupied(new Vector2d(1,1)));
        assertEquals(animal1, map.objectAt(new Vector2d(1,1)));
    }

    @Test
    public void placeAnimalFalse() {
        // given
        RectangularMap map = new RectangularMap(3,7);
        Animal animal1 = new Animal(new Vector2d(1,1));
        Animal animal2 = new Animal(new Vector2d(10,10));
        Animal animal3 = new Animal(new Vector2d(3,3));
        Animal animal4 = new Animal(new Vector2d(3,3));

        map.place(animal1);
        map.place(animal3);

        // when then
        assertFalse(map.place(animal1)); // już na mapie
        assertFalse(map.place(animal2)); // poza granicami mapy
        assertFalse(map.place(animal4)); // zajęte miejsce
    }


    @Test
    public void moveWhenAnimalMoves() {
        // given
        RectangularMap map = new RectangularMap(4, 7);
        Animal animal1 = new Animal(new Vector2d(2,2));
        MoveDirection direction = MoveDirection.FORWARD;

        map.place(animal1);

        // when
        map.move(animal1, direction);

        // then
        assertTrue(map.isOccupied(new Vector2d(2,3)));
        assertEquals(animal1, map.objectAt(new Vector2d(2,3)));
    }


    @Test
    public void moveWhenAnimalDoesntMoveBehindBorders() {
        // given
        RectangularMap map = new RectangularMap(4, 7);
        Animal animal1 = new Animal(new Vector2d(4,7));
        MoveDirection direction = MoveDirection.FORWARD;

        map.place(animal1);

        // when
        map.move(animal1, direction);

        // then
        assertFalse(map.isOccupied(new Vector2d(3,7)));
        assertEquals(animal1, map.objectAt(new Vector2d(4,7)));
    }

    @Test
    public void moveWhenAnimalDoesntMoveBecausePositionOccupied() {
        // given
        RectangularMap map = new RectangularMap(4, 7);
        Animal animal1 = new Animal(new Vector2d(4,6));
        Animal animal2 = new Animal(new Vector2d(4,7));
        MoveDirection direction = MoveDirection.FORWARD;

        map.place(animal1);
        map.place(animal2);

        // when
        map.move(animal1, direction);

        // then
        assertEquals(animal1, map.objectAt(new Vector2d(4,6)));
        assertEquals(animal2, map.objectAt(new Vector2d(4,7)));
    }

}