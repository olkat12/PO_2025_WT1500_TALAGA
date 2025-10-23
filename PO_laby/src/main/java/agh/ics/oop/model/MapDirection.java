package agh.ics.oop.model;


public enum MapDirection {

    NORTH,
    EAST,
    SOUTH,
    WEST;

    public String toString() {

        return switch (this) {
            case NORTH -> "Północ";
            case EAST -> "Wschod";
            case SOUTH -> "Południe";
            case WEST -> "Zachod";
        };
    }

    public MapDirection next()
    {
        MapDirection direction = switch(this)
        {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        };
        return direction;
    }

    public MapDirection previous()
    {
        MapDirection direction = switch(this)
        {
            case NORTH -> WEST;
            case EAST -> NORTH;
            case SOUTH -> EAST;
            case WEST -> SOUTH;
        };
        return direction;
    }

    public Vector2d toUnitVector()
    {
        Vector2d wektor = switch(this)
        {
            case NORTH -> new Vector2d(0,1);
            case EAST -> new Vector2d(1,0);
            case SOUTH -> new Vector2d(0,-1);
            case WEST -> new Vector2d(-1,0);
        };

        return wektor;
    }
}
