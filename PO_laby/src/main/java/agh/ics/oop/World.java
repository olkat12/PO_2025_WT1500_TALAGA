package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;

// Uruchomienie w PowerShell:
//$env:JAVA_HOME = "C:\Program Files\Java\jdk-25"
//.\gradlew.bat run --args="f b r l"

public class World {

    public static void main(String[] args)
    {
        System.out.println("System wystartowal");
        //run(OptionsParser.parse(args));
        System.out.println("System zakonczyl dzialanie");
    }

    public static void run(MoveDirection[] tab)
    {
        for(int i = 0; i < tab.length; i++ )
        {
            switch(tab[i])
            {
                case MoveDirection.FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case MoveDirection.BACKWARD -> System.out.println("Zwierzak idzie do tylu");
                case MoveDirection.RIGHT -> System.out.println("Zwierzak skreca w prawo");
                case MoveDirection.LEFT -> System.out.println("Zwierzak skreca w lewo");
                default -> System.out.print("");
            }
        }

        // Sposób 1
        //System.out.print(String.join(", ", args));

        // Sposób 2
//        for (int i = 0; i < args.length - 2; i++) {
//            System.out.print(args[i] + ", ");
//        }
//        System.out.println(args[args.length - 1]);

    }
}
