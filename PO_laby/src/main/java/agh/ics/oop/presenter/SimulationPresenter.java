package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;


public class SimulationPresenter implements MapChangeListener {

    private WorldMap worldMap;

    @FXML
    private Label infoLabel;

    @FXML
    private TextField movesField;

    @FXML
    private Label moveInfoLabel;

    @FXML
    private Button startButton;

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void drawMap()
    {
        if (worldMap == null)
        {
            infoLabel.setText("WorldMap not set");
            return;
        }
        infoLabel.setText(worldMap.toString());
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message)
    {
        this.drawMap();
        moveInfoLabel.setText( message);
    }

    public void onSimulationStartClicked() {

        String input = movesField.getText();
        String[] args = input.split(" ");

        List<Vector2d> positions = List.of(new Vector2d(2, 7), new Vector2d(4, 1));
        List<MoveDirection > movements = OptionsParser.parse(args);
        Simulation simulation = new Simulation(positions, movements, this.worldMap);
        simulation.run();

    }
}
