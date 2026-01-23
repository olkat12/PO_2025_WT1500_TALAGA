package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.List;


public class SimulationPresenter implements MapChangeListener {

    private WorldMap worldMap;
    private int widthInCells;
    private int heightInCells;
    private static final double CELL_SIZE = 40; // 10 pikseli na komórkę
    private static final double BORDER_WIDTH = 2.0;

    // przesuniecie żeby była wycentrowana linia
    private static final double BORDER_OFFSET = BORDER_WIDTH / 2;


    @FXML
    private TextField movesField;

    @FXML
    private Label moveInfoLabel;

    @FXML
    private Button startButton;

    @FXML
    private Canvas mapGrid;



    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    private void calculateBorders() {

        Boundary bounds = worldMap.getCurrentBounds();
        Vector2d lower = bounds.lowerLeft();
        Vector2d upper = bounds.upperRight();

        this.widthInCells = upper.getX() - lower.getX() + 1;
        this.heightInCells = upper.getY() - lower.getY() + 1;

        // ustawienie rozmiaru Canvas
        mapGrid.setWidth(widthInCells * CELL_SIZE + BORDER_WIDTH);
        mapGrid.setHeight(heightInCells * CELL_SIZE + BORDER_WIDTH);
    }

    public void drawMap() {

        Boundary bounds = worldMap.getCurrentBounds();
        Vector2d lower = bounds.lowerLeft();
        Vector2d upper = bounds.upperRight();

        this.widthInCells = upper.getX() - lower.getX() + 1;
        this.heightInCells = upper.getY() - lower.getY() + 1;

        mapGrid.setWidth(widthInCells * CELL_SIZE + BORDER_WIDTH);
        mapGrid.setHeight(heightInCells * CELL_SIZE + BORDER_WIDTH);

        GraphicsContext gc = mapGrid.getGraphicsContext2D();

        // Czyszczenie Canvas
        clearGrid();

        // Konfiguracja czcionki
        configureFont(gc, 14, Color.BLACK);

        // Rysowanie współrzędnych X
        for (int x = 0; x < widthInCells; x++) {
            double xPixel = x * CELL_SIZE + CELL_SIZE / 2 + BORDER_OFFSET;
            gc.fillText(String.valueOf(lower.getX() + x), xPixel, 12); // 12 px od góry
        }

        // Rysowanie współrzędnych Y
        for (int y = 0; y < heightInCells; y++) {
            double yPixel = (heightInCells - 1 - y) * CELL_SIZE + CELL_SIZE / 2 + BORDER_OFFSET;
            gc.fillText(String.valueOf(lower.getY() + y), 12, yPixel); // 12 px od lewej
        }

        // Rysowanie siatki
        drawGrid(gc);

        // Rysowanie obiektów w kratkach
        for (int x = 0; x < widthInCells; x++) {
            for (int y = 0; y < heightInCells; y++) {
                Vector2d position = new Vector2d(lower.getX() + x, lower.getY() + y);
                Object obj = worldMap.objectAt(position);
                if (obj != null) {
                    double xPixel = x * CELL_SIZE + CELL_SIZE / 2 + BORDER_OFFSET;
                    double yPixel = (heightInCells - 1 - y) * CELL_SIZE + CELL_SIZE / 2 + BORDER_OFFSET;
                    gc.fillText(obj.toString(), xPixel, yPixel);
                }
            }
        }
    }



    // Pomocnicza metoda do konfiguracji czcionki
    private void configureFont(GraphicsContext graphics, int size, Color color) {
        graphics.setTextAlign(TextAlignment.CENTER);
        graphics.setTextBaseline(VPos.CENTER);
        graphics.setFont(new Font("Arial", size));
        graphics.setFill(color);
    }

    // Czyszczenie Canvas
    private void clearGrid() {
        GraphicsContext graphics = mapGrid.getGraphicsContext2D();
        graphics.setFill(Color.WHITE);
        graphics.fillRect(0, 0, mapGrid.getWidth(), mapGrid.getHeight());
    }

    // Rysowanie siatki
    private void drawGrid(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(BORDER_WIDTH);

        double canvasWidth = widthInCells * CELL_SIZE;
        double canvasHeight = heightInCells * CELL_SIZE;

        for (int x = 0; x <= widthInCells; x++) {
            double xPos = x * CELL_SIZE + BORDER_OFFSET;
            if (x == widthInCells) xPos -= BORDER_WIDTH;
            gc.strokeLine(xPos, 0, xPos, canvasHeight);
        }

        for (int y = 0; y <= heightInCells; y++) {
            double yPos = y * CELL_SIZE + BORDER_OFFSET;
            if (y == heightInCells) yPos -= BORDER_WIDTH;
            gc.strokeLine(0, yPos, canvasWidth, yPos);
        }
    }



    @Override
    public void mapChanged(WorldMap worldMap, String message)
    {
        Platform.runLater(() -> {
            drawMap();
            moveInfoLabel.setText(message);
        });
    }

    public void onSimulationStartClicked() {

        String input = movesField.getText();
        String[] args = input.split(" ");

        List<Vector2d> positions = List.of(new Vector2d(2, 7), new Vector2d(4, 1));
        List<MoveDirection > movements = OptionsParser.parse(args);
        Simulation simulation = new Simulation(positions, movements, this.worldMap);
        new Thread(simulation).start();

    }
}
