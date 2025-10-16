package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;
import java.util.Arrays;

public class OptionsParser {

    public static MoveDirection[] parse(String[] args)
    {
        MoveDirection[] enum_tab = new MoveDirection[args.length];

        int counter = 0; // licznik poprawnych elementow
        for(int i = 0; i < args.length; i++)
        {
            MoveDirection dir = switch (args[i])
            {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "r" -> MoveDirection.RIGHT;
                case "l" -> MoveDirection.LEFT;
                default -> null;
            };
            if(dir != null)
            {
                enum_tab[counter] = dir;
                counter++;
            }
        }

        return Arrays.copyOfRange(enum_tab, 0, counter);
        // Na końcu tablicy będą same null
        // Więc chcemy zwrócić tylko fragment od początku do licznika
    }
}
