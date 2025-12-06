package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SimulationApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(); // zainicjowanie wczytywania FXML

        // wczytanie zasobu z katalogu resources (uniwersalny sposób)
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));

        // Wczytanie FXML, konwersja FXML -> obiekty w Javie
        BorderPane viewRoot = loader.load();
        SimulationPresenter presenter = loader.getController();

        RectangularMap rectangularMap = new RectangularMap(5,10);
        rectangularMap.addListener(presenter);
        presenter.setWorldMap(rectangularMap);

        
        presenter.drawMap();
        Animal animal1 = new Animal(new Vector2d(2, 5));
        rectangularMap.place(animal1);

        configureStage(primaryStage, viewRoot);
        primaryStage.show();
    }

    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        // stworzenie sceny (panelu do wyświetlania wraz zawartością z FXML)
        var scene = new Scene(viewRoot);

        // ustawienie sceny w oknie
        primaryStage.setScene(scene);

        // konfiguracja okna
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }

}
