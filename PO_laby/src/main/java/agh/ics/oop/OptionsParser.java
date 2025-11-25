package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import java.util.ArrayList;
import java.util.List;

public class OptionsParser {

    // ArrayList w przeciwieństwie do tablicy nie musi mieć podanego rozmiaru z góry
    public static List<MoveDirection> parse(String[] args)
    {
       List<MoveDirection> enumTab = new ArrayList<>();

        for(String move : args)
        {
            switch (move)
            {
                case "f" -> enumTab.add(MoveDirection.FORWARD);
                case "b" -> enumTab.add(MoveDirection.BACKWARD);
                case "r" -> enumTab.add(MoveDirection.RIGHT);
                case "l" -> enumTab.add(MoveDirection.LEFT);
                default -> throw new IllegalArgumentException(move + " is not legal move specification");
            }
        }
        return  enumTab;
    }
}
