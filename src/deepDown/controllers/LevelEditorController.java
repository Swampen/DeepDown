package deepDown.controllers;

import deepDown.Alerts;
import deepDown.Loader;
import deepDown.level.LevelEditor;
import deepDown.level.LevelReader;
import deepDown.level.LevelRequirements;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;

/**
 * @author Ole-Martin Heggen
 */
public class LevelEditorController {

    @FXML private Canvas editorCanvas;
    @FXML private AnchorPane anchor;

    private double xMouseClicked;
    private double yMouseClicked;
    private AnimationTimer animationTimer;
    private int[][] levelArray;
    private int selectedTile;
    private final BooleanProperty mouseClicked = new SimpleBooleanProperty(false);
    private final BooleanProperty mouseDragged = new SimpleBooleanProperty(false);
    private LevelEditor levelEditor;
    private File custom;
    private String filepath;
    private String file;

    /**
     * Method which runs when the fxml is loaded.
     * Initializes the editor, sets action on mouse clicked or dragged
     * and starts the animationTimer.
     */
    @FXML
    public void initialize(){
        GraphicsContext gc = editorCanvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, editorCanvas.getWidth(), editorCanvas.getHeight());
        levelEditor = new LevelEditor(gc);

        filepath = new File("Files").getAbsolutePath();
        file = "customLevel.txt";
        custom = new File(filepath, file);
        try {
            if(custom.exists()) {
                loadLevelPressed();
            } else if(!custom.exists()){
                levelArray = levelEditor.getLevelArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        editorCanvas.setOnMouseDragged(e -> {
            if (selectedTile == 0 || selectedTile == 1 || selectedTile == 2) {
                yMouseClicked = e.getY();
                xMouseClicked = e.getX();
            }
        });
        editorCanvas.setOnMousePressed(e -> {
            mouseClicked.set(true);
            yMouseClicked = e.getY();
            xMouseClicked = e.getX();
        });

        editorCanvas.setOnMouseReleased(e -> {
            mouseDragged.set(false);
            mouseClicked.set(false);
            yMouseClicked = e.getY();
            xMouseClicked = e.getX();
        });


        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (mouseClicked.get()) {
                    int yToDraw = 0;
                    int xToDraw = 0;
                    int levelArrayY = -1;
                    int levelArrayX = -1;

                    while (yToDraw < yMouseClicked){
                        yToDraw += 32;
                        levelArrayY += 1;
                    }
                    while (xToDraw < xMouseClicked){
                        xToDraw += 32;
                        levelArrayX += 1;
                    }
                    int selectedGrid = levelArray[levelArrayY][levelArrayX];

                    if (LevelRequirements.isKeyLimit() && selectedGrid == 5 && selectedTile != 5) {
                        LevelRequirements.setKeyLimit(false);
                    }
                    if (LevelRequirements.isDoorLimit() && selectedGrid == 6 && selectedTile != 6) {
                        LevelRequirements.setDoorLimit(false);
                    }
                    if (LevelRequirements.isAvatarLimit() && selectedGrid == 7 && selectedTile != 7) {
                        LevelRequirements.setAvatarLimit(false);
                    }

                    if (!(LevelRequirements.isKeyLimit() && selectedTile == 5) &&
                            !(LevelRequirements.isDoorLimit() && selectedTile == 6) &&
                            !(LevelRequirements.isAvatarLimit() && selectedTile == 7)){
                        levelArray[levelArrayY][levelArrayX] = selectedTile;
                    }

                    levelEditor.updateEditorCanvas(levelArray);
                }
            }
        };
        animationTimer.start();
    }

    /**
     * The action performed when the "Play Level" button is pressed.
     * Starts the custom level in it's state.
     * Shows an error if the level requirements is not met.
     */
    public void playLevelPressed(){
        if (LevelRequirements.isValidLevel(levelArray)) {
            levelEditor.saveCustomLevel(levelArray, custom);
            animationTimer.stop();
            Loader.loadLevel(anchor, 9, 0, 40);
        }else {
            Alerts.notValidLevel();
        }
    }

    /**
     * The action performed when the "Save Level" button is pressed.
     * Saves the custom level in its current state.
     */
    public void saveLevelPressed(){
        levelEditor.saveCustomLevel(levelArray, custom);
    }

    /**
     * The action performed when the "Load Level" button is pressed.
     * Loads a saved custom level if it exists.
     */
    public void loadLevelPressed(){
        levelArray = LevelReader.readCustomLevel();
        levelEditor.updateEditorCanvas(levelArray);
    }

    /**
     * The action performed when the "Back to Menu" button is pressed.
     * Loads the start menu with FXML.
     */
    public void backToMenuPressed(){
        Loader.loadStartMenu(anchor);
    }

    /**
     * The action performed when the "Discard" button is pressed.
     * Shows an alertbox, and discards the custom level based on the response.
     */
    public void discardPressed(){
        if (Alerts.discardCustomLevel()) {
            levelArray = levelEditor.getLevelArray();
        }
    }

    /**
     * The action performed when the "Eracer" button is pressed.
     * Selects the eraser tool.
     */
    public void eraserButtonPressed(){
        selectedTile = 0;
        System.out.println("eraser: " + selectedTile);
    }

    /**
     * The action performed when the "Wall" button is pressed.
     * Selects the wall tool.
     */
    public void wallButtonPressed(){
        selectedTile = 1;
        System.out.println("wall: " + selectedTile);
    }

    /**
     * The action performed when the "Coin" button is pressed.
     * Selects the coin tool.
     */
    public void coinButtonPressed(){
        selectedTile = 2;
        System.out.println("coin: " + selectedTile);
    }

    /**
     * The action performed when the "Horizontal Enemy" button is pressed.
     * Selects the horizontal enemy tool.
     */
    public void horizontalEnemyButtonPressed(){
        selectedTile = 3;
        System.out.println("enemy: " + selectedTile);
    }

    /**
     * The action performed when the "Vertical Enemy" button is pressed.
     * Selects the vertical enemy tool.
     */
    public void verticalEnemyButtonPressed(){
        selectedTile = 4;
        System.out.println("enemy: " + selectedTile);
    }

    /**
     * The action performed when the "Key" button is pressed.
     * Selects the key enemy tool.
     */
    public void keyButtonPressed(){
        selectedTile = 5;
        System.out.println("key: " + selectedTile);
    }

    /**
     * The action performed when the "Door" button is pressed.
     * Selects the door enemy tool.
     */
    public void doorButtonPressed(){
        selectedTile = 6;
        System.out.println("door: " + selectedTile);
    }

    /**
     * The action performed when the "Avatar" button is pressed.
     * Selects the avatar enemy tool.
     */
    public void avatarButtonPressed(){
        selectedTile = 7;
        System.out.println("avatar: " + selectedTile);
    }

}
