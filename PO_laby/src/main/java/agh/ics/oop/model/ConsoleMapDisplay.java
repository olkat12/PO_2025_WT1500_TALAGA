package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {

    private int counter = 0;

    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {

        System.out.println("\nMap id: " + worldMap.getId());
        System.out.println("Message: " + message);
        System.out.println(worldMap.toString());
        counter++;
        System.out.println("Number of events: " + counter);
    }
}
