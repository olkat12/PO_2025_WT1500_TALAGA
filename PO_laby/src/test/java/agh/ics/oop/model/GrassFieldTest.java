package agh.ics.oop.model;

import agh.ics.oop.model.util.IncorrectPositionException;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


class GrassFieldTest {

    @Test
    public void grassFieldSetup() {
        // given
        GrassField grassField = new GrassField(10);

        // when then
        assertEquals(10, grassField.getElements().size());
    }

    @Test
    public void getElementsOnlyGrasses() {
        // given
        GrassField grassField = new GrassField(10);

        // when
        List<WorldElement> elements = grassField.getElements();

        // then
        for (WorldElement el : elements) {
            assertInstanceOf(Grass.class, el);
        }
    }

    @Test
    public void objectAtWhenGrass() {
        // given
        GrassField grassField = new GrassField(10);

        // when then
        assertInstanceOf(Grass.class, grassField.objectAt(new Vector2d(0, 10)));
    }

    @Test
    public void objectAtNullWhenEmpty() {
        // given
        GrassField grassField = new GrassField(10);

        // when then
        assertNull(grassField.objectAt(new Vector2d(5, 10)));
    }

    @Test
    public void objectAtWhenAnimal() throws IncorrectPositionException{
        // given
        GrassField grassField = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(2, 4));
        grassField.place(animal1);

        // when then
        assertEquals(grassField.getElements().get(0), grassField.objectAt(new Vector2d(2, 4)));
        assertInstanceOf(Animal.class, grassField.objectAt(new Vector2d(2, 4)));
    }

    @Test
    public void objectAtReturnsAnimalWhenAnimalAndGrass() throws IncorrectPositionException{
        // given
        GrassField grassField = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(0, 10));
        grassField.place(animal1);

        // when then
        assertEquals(grassField.getElements().get(0), grassField.objectAt(new Vector2d(0, 10)));
        assertInstanceOf(Animal.class, grassField.objectAt(new Vector2d(0, 10)));
    }


    @Test
    public void isOccupiedTrueWhenGrass() {
        //given
        GrassField grassField = new GrassField(10);

        // when then
        assertTrue(grassField.isOccupied(new Vector2d(0, 10)));
    }

    @Test
    public void isOccupiedFalseWhenEmpty() {
        //given
        GrassField grassField = new GrassField(10);

        // when then
        assertFalse(grassField.isOccupied(new Vector2d(8, 0)));
    }

    @Test
    public void isOccupiedTrueWhenAnimal() throws IncorrectPositionException {
        // given
        GrassField grassField = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(2, 4));
        grassField.place(animal1);

        // when then
        assertTrue(grassField.isOccupied(new Vector2d(2, 4)));
    }

    @Test
    public void canMoveToTrueWhenEmpty() {
        // given
        GrassField grassField = new GrassField(10);

        // when then
        assertTrue(grassField.canMoveTo(new Vector2d(7, 7)));
        assertNull(grassField.objectAt(new Vector2d(7, 7)));
    }

    @Test
    public void canMoveToTrueWhenGrass() {
        // given
        GrassField grassField = new GrassField(10);

        // when then
        assertTrue(grassField.canMoveTo(new Vector2d(0, 10)));
        assertInstanceOf(Grass.class, grassField.objectAt(new Vector2d(0, 10)));
    }

    @Test
    public void canMoveToFalseWhenAnimal() throws IncorrectPositionException {
        // given
        GrassField grassField = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(1, 1));
        grassField.place(animal1);

        assertFalse(grassField.canMoveTo(new Vector2d(1, 1)));
        assertInstanceOf(Animal.class, grassField.objectAt(new Vector2d(1, 1)));
    }

    @Test
    public void placeAnimalTrue() throws IncorrectPositionException{

        // given
        GrassField grassField = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(1, 1));
        grassField.place(animal1);

        // when then
        assertTrue(grassField.isOccupied(new Vector2d(1, 1)));
        assertEquals(animal1, grassField.objectAt(new Vector2d(1, 1)));
    }

    @Test
    public void placeAnimalThrowsExceptionBecausePlaceIsOccupied() throws IncorrectPositionException {

        // given
        GrassField grassField = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(1, 1));
        Animal animal2 = new Animal(new Vector2d(1, 1));
        grassField.place(animal1);

        // when then
        assertThrows(IncorrectPositionException.class, () -> { grassField.place(animal2);});
        assertTrue(grassField.isOccupied(new Vector2d(1, 1)));
        assertEquals(animal1, grassField.objectAt(new Vector2d(1, 1)));
    }

    @Test
    public void placeAnimalTrueWhenGrassOnPlace() throws IncorrectPositionException {

        // given
        GrassField grassField = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(0, 10));
        grassField.place(animal1);


        // when then
        assertInstanceOf(Animal.class, grassField.objectAt(new Vector2d(0, 10)));
        assertEquals(animal1, grassField.objectAt(new Vector2d(0, 10)));
    }

    @Test
    public void moveWhenAnimalMoves() throws IncorrectPositionException {

        // given
        GrassField grassField = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        MoveDirection direction = MoveDirection.FORWARD;

        grassField.place(animal1);

        // when
        grassField.move(animal1, direction);

        // then
        assertTrue(grassField.isOccupied(new Vector2d(2, 3)));
        assertEquals(animal1, grassField.objectAt(new Vector2d(2, 3)));
    }

    @Test
    public void moveWhenAnimalDoesntMoveBecausePositionOccupied() throws IncorrectPositionException {
        // given
        GrassField grassField = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(4, 6));
        Animal animal2 = new Animal(new Vector2d(4, 7));
        MoveDirection direction = MoveDirection.FORWARD;

        grassField.place(animal1);
        grassField.place(animal2);

        // when
        grassField.move(animal1, direction);

        // then
        assertFalse(grassField.canMoveTo(new Vector2d(4,6)));
        assertTrue(grassField.isOccupied(new Vector2d(4, 6)));
        assertEquals(animal1, grassField.objectAt(new Vector2d(4, 6)));
    }

}




