package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    @Test
    void runAllProperDirections()
    {
        // given
        String[] args = {"f", "r", "l", "f"};

        List<MoveDirection> movements = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(0,0), new Vector2d(1,1));
        RectangularMap map = new RectangularMap(4,4);

        // oczekiwane wyniki
        Vector2d position1 = new Vector2d(0,1);
        MapDirection direction1 = MapDirection.WEST;

        Vector2d position2 = new Vector2d(2,1);
        MapDirection direction2 = MapDirection.EAST;

        //when
        Simulation simulation = new Simulation(positions, movements, map);
        List<Animal> animals = simulation.getAnimals();
        simulation.run();

        //then
        assertEquals(position1, animals.get(0).getPosition());
        assertEquals(position2, animals.get(1).getPosition());

        assertEquals(direction1, animals.get(0).getMapDirection());
        assertEquals(direction2, animals.get(1).getMapDirection());
    }

    @Test
    void runEmptyDirections()
    {
        // given
        String[] args = {};

        List<MoveDirection> movements = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(0,0), new Vector2d(1,1));
        RectangularMap map = new RectangularMap(4,4);

        // oczekiwane
        Vector2d position1 = new Vector2d(0,0);
        MapDirection direction1 = MapDirection.NORTH;

        Vector2d position2 = new Vector2d(1,1);
        MapDirection direction2 = MapDirection.NORTH;

        //when
        Simulation simulation = new Simulation(positions, movements, map);
        List<Animal> animals = simulation.getAnimals();
        simulation.run();

        //then
        assertEquals(position1, animals.get(0).getPosition());
        assertEquals(position2, animals.get(1).getPosition());

        assertEquals(direction1, animals.get(0).getMapDirection());
        assertEquals(direction2, animals.get(1).getMapDirection());
    }

    @Test
    void runAllWrongDirections()
    {
        // given
        String[] args = {"g", "1234", "ola"};

        List<MoveDirection> movements = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(0,0), new Vector2d(1,1));
        RectangularMap map = new RectangularMap(4,4);

        // oczekiwane
        Vector2d position1 = new Vector2d(0,0);
        MapDirection direction1 = MapDirection.NORTH;

        Vector2d position2 = new Vector2d(1,1);
        MapDirection direction2 = MapDirection.NORTH;

        //when
        Simulation simulation = new Simulation(positions, movements, map);
        List<Animal> animals = simulation.getAnimals();
        simulation.run();

        //then
        assertEquals(position1, animals.get(0).getPosition());
        assertEquals(position2, animals.get(1).getPosition());

        assertEquals(direction1, animals.get(0).getMapDirection());
        assertEquals(direction2, animals.get(1).getMapDirection());
    }

    @Test
    void runProperandWrongDirections()
    {
        // given
        String[] args = {"f", "r", "123", "wrong", "l", "f"};

        List<MoveDirection> movements = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(0,0), new Vector2d(1,1));
        RectangularMap map = new RectangularMap(4,4);

        // oczekiwane
        Vector2d position1 = new Vector2d(0,1);
        MapDirection direction1 = MapDirection.WEST;

        Vector2d position2 = new Vector2d(2,1);
        MapDirection direction2 = MapDirection.EAST;

        //when
        Simulation simulation = new Simulation(positions, movements, map);
        List<Animal> animals = simulation.getAnimals();
        simulation.run();

        //then
        assertEquals(position1, animals.get(0).getPosition());
        assertEquals(position2, animals.get(1).getPosition());

        assertEquals(direction1, animals.get(0).getMapDirection());
        assertEquals(direction2, animals.get(1).getMapDirection());
    }



    // Testy Simulation i WorldMap



    @Test
    void runProperDirectionsRectangularMap()
    {
        // given
        String[] args = {"f", "r"};

        List<MoveDirection> movements = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(0,0), new Vector2d(3,3));
        RectangularMap map = new RectangularMap(4,7);

        //when
        Simulation simulation = new Simulation(positions, movements, map);
        simulation.run();

        // debug - sprawdzenie kolejności
        System.out.println("Animals:");
        simulation.getAnimals().forEach(System.out::println);

        System.out.println("Map elements:");
        map.getElements().forEach(System.out::println);


        //then
        assertEquals(simulation.getAnimals().get(0), map.getElements().get(1));
        assertEquals(MapDirection.NORTH, ((Animal) map.getElements().get(1)).getMapDirection());
        assertTrue(map.isOccupied(new Vector2d(0, 1)));

        assertEquals(simulation.getAnimals().get(1), map.getElements().get(0));
        assertEquals(MapDirection.EAST, ((Animal) map.getElements().get(0)).getMapDirection());
        assertTrue(map.isOccupied(new Vector2d(3, 3)));
    }

    @Test
    void runWrongDirectionsRectangularMap()
    {
        // given
        String[] args = {"ola", "ala"};

        List<MoveDirection> movements = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(0,0), new Vector2d(3,3));
        RectangularMap map = new RectangularMap(4,7);

        //when
        Simulation simulation = new Simulation(positions, movements, map);
        simulation.run();


        //then
        assertEquals(simulation.getAnimals().get(0), map.getElements().get(0));
        assertEquals(MapDirection.NORTH, ((Animal) map.getElements().get(0)).getMapDirection());
        assertTrue(map.isOccupied(new Vector2d(0, 0)));
        assertFalse(map.isOccupied(new Vector2d(0, 1)));

        assertEquals(simulation.getAnimals().get(1), map.getElements().get(1));
        assertEquals(MapDirection.NORTH, ((Animal) map.getElements().get(1)).getMapDirection());
        assertTrue(map.isOccupied(new Vector2d(3, 3)));

    }

}