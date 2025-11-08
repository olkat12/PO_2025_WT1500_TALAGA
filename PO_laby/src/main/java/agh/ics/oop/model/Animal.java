package agh.ics.oop.model;

import java.util.Objects;

public class Animal {

    private MapDirection mapDirection;
    private Vector2d position;

    private static final Vector2d UPPER = new Vector2d(4,4);
    private static final Vector2d LOWER = new Vector2d(0,0);


    public MapDirection getMapDirection() {
        return mapDirection;
    }

    public Vector2d getPosition() {
        return position;
    }

    public Animal(Vector2d position)
    {
        this.position = position;
        this.mapDirection = MapDirection.NORTH;
    }

    public Animal()
    {
        this(new Vector2d(2,2));
    }

    @Override
    public String toString() {

        //return position.toString() + " " + mapDirection.toString();
        return switch(this.mapDirection)
        {
            case NORTH -> "^";
            case EAST -> ">";
            case SOUTH -> "v";
            case WEST -> "<";
        };

    }

    public boolean isAt(Vector2d position)
    {
        return Objects.equals(this.position, position);
    }

    public void move(MoveDirection movement, MoveValidator moveValidator) {

        Vector2d currentPosition = position;

        mapDirection = switch (movement) {
            case RIGHT ->  mapDirection.next();
            case LEFT -> mapDirection.previous();
            case FORWARD, BACKWARD -> mapDirection;
        };

        position = switch (movement)
        {
            case FORWARD -> position.add(mapDirection.toUnitVector());
            case BACKWARD -> position.subtract(mapDirection.toUnitVector());
            case RIGHT, LEFT -> position;
        };

        if(!moveValidator.canMoveTo(position))
        {
            position = currentPosition;
        }
    }

}
