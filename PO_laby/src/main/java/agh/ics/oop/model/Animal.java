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

        return position.toString() + " " + mapDirection.toString();
        //return "pozycja: %s orientacja: %s".formatted(position, mapDirection)
    }

    public boolean isAt(Vector2d position)
    {
        return Objects.equals(this.position, position);
    }

    public void move(MoveDirection movement) {

        Vector2d current_position = position;

        switch (movement) {
            case RIGHT -> mapDirection = mapDirection.next();
            case LEFT -> mapDirection = mapDirection.previous();
            case FORWARD -> position = position.add(mapDirection.toUnitVector());
            case BACKWARD -> position = position.subtract(mapDirection.toUnitVector());
        };
        if(position.getX() > UPPER.getX() || position.getX() < LOWER.getX()
                || position.getY() > UPPER.getY() || position.getY() < LOWER.getY())
        {
            position = current_position;
        }
    }
}
