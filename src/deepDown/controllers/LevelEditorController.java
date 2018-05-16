package deepDown.controllers;

import deepDown.Alerts;
import deepDown.ButtonProperty;
import deepDown.Loader;
import deepDown.level.LevelEditor;
import deepDown.level.LevelReader;
import deepDown.level.LevelRequirements;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.File;

/**
 * @author Ole-Martin Heggen
 */
public class LevelEditorController {

    @FXML private Canvas editorCanvas;
    @FXML private AnchorPane anchor;
    @FXML private Button eraserButton;
    @FXML private Button wallButton;
    @FXML private Button coinButton;
    @FXML private Button horizontalEnemyButton;
    @FXML private Button verticalEnemyButton;
    @FXML private Button keyButton;
    @FXML private Button doorButton;
    @FXML private Button avatarButton;

    private double xMouseClicked;
    private double yMouseClicked;
    private AnimationTimer animationTimer;
    private int[][] levelArray;
    private int selectedTile;
    private final BooleanProperty mouseClicked = new SimpleBooleanProperty(false);
    private LevelEditor levelEditor;
    private File custom;
    private int gridSize;

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
        gridSize = levelEditor.getGridSize();

        eraserButton = ButtonProperty.setStaticButtonImage
                ("/deepDown/resource/images/levelEditor/Eraser.png", eraserButton);
        wallButton = ButtonProperty.setStaticButtonImage
                ("/deepDown/resource/images/levelEditor/Wall.png", wallButton);
        coinButton = ButtonProperty.setStaticButtonImage
                ("/deepDown/resource/images/levelEditor/Coin.png", coinButton);
        horizontalEnemyButton = ButtonProperty.setStaticButtonImage
                ("/deepDown/resource/images/levelEditor/HorizontalEnemy.png", horizontalEnemyButton);
        verticalEnemyButton = ButtonProperty.setStaticButtonImage
                ("/deepDown/resource/images/levelEditor/VerticalEnemy.png", verticalEnemyButton);
        keyButton = ButtonProperty.setStaticButtonImage
                ("/deepDown/resource/images/levelEditor/Key.png", keyButton);
        doorButton = ButtonProperty.setStaticButtonImage
                ("/deepDown/resource/images/levelEditor/Door.png", doorButton);
        avatarButton = ButtonProperty.setStaticButtonImage
                ("/deepDown/resource/images/levelEditor/Avatar.png", avatarButton);

        anchor.getStylesheets().add("deepDown/resource/stylesheet.css");


        String filepath = new File("Files").getAbsolutePath();
        String file = "customLevel.txt";
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
                xMouseClicked = e.getX();
                yMouseClicked = e.getY();
            }
            if (e.getX() <= gridSize || e.getX() >= editorCanvas.getWidth() - gridSize ||
                    e.getY() <= gridSize || e.getY() >= editorCanvas.getHeight() - gridSize ){
                mouseClicked.set(false);
            }else {
                mouseClicked.set(true);
            }
        });
        editorCanvas.setOnMousePressed(e -> {
            if (e.getX() > gridSize && e.getX() < editorCanvas.getWidth() - gridSize &&
                    e.getY() > gridSize && e.getY() < editorCanvas.getHeight() - gridSize ){
                mouseClicked.set(true);
            }
            xMouseClicked = e.getX();
            yMouseClicked = e.getY();
        });

        editorCanvas.setOnMouseReleased(e -> mouseClicked.set(false));


        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (mouseClicked.get()) {
                    int xToDraw = 0;
                    int yToDraw = 0;

                    int levelArrayX = -1;
                    int levelArrayY = -1;

                    while (xToDraw < xMouseClicked){
                        xToDraw += 32;
                        levelArrayX += 1;
                    }
                    while (yToDraw < yMouseClicked){
                        yToDraw += 32;
                        levelArrayY += 1;
                    }
                    int selectedGrid = levelArray[levelArrayY][levelArrayX];

                    if (LevelRequirements.isKeyLimit() && selectedGrid != 5 && selectedTile != 5) {
                        LevelRequirements.setKeyLimit(false);
                    }
                    if (LevelRequirements.isDoorLimit() && selectedGrid != 6 && selectedTile != 6) {
                        LevelRequirements.setDoorLimit(false);
                    }
                    if (LevelRequirements.isAvatarLimit() && selectedGrid != 7 && selectedTile != 7) {
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
        if (LevelRequirements.isValidLevel()) {
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
        if (levelArray == null){
            levelArray = levelEditor.getLevelArray();
            Alerts.noCustomLevel();
        }
        levelEditor.updateEditorCanvas(levelArray);
    }

    /**
     * The action performed when the "Back to Menu" button is pressed.
     * Loads the start menu with FXML.
     */
    public void backToMenuPressed(){
        animationTimer.stop();
        Loader.loadStartMenu(anchor);
    }

    /**
     * The action performed when the "Discard" button is pressed.
     * Shows an alert box, and discards the custom level based on the response.
     */
    public void discardPressed(){
        if (Alerts.discardCustomLevel()) {
            levelArray = levelEditor.getLevelArray();
        }
    }

    /**
     * The action performed when the "Eraser" button is pressed.
     * Selects the eraser tool.
     */
    public void eraserButtonPressed(){
        selectedTile = 0;
    }

    /**
     * The action performed when the "Wall" button is pressed.
     * Selects the wall tool.
     */
    public void wallButtonPressed(){
        selectedTile = 1;
    }

    /**
     * The action performed when the "Coin" button is pressed.
     * Selects the coin tool.
     */
    public void coinButtonPressed(){
        selectedTile = 2;
    }

    /**
     * The action performed when the "Horizontal Enemy" button is pressed.
     * Selects the horizontal enemy tool.
     */
    public void horizontalEnemyButtonPressed(){
        selectedTile = 3;
    }

    /**
     * The action performed when the "Vertical Enemy" button is pressed.
     * Selects the vertical enemy tool.
     */
    public void verticalEnemyButtonPressed(){
        selectedTile = 4;
    }

    /**
     * The action performed when the "Key" button is pressed.
     * Selects the key enemy tool.
     */
    public void keyButtonPressed(){
        selectedTile = 5;
    }

    /**
     * The action performed when the "Door" button is pressed.
     * Selects the door enemy tool.
     */
    public void doorButtonPressed(){
        selectedTile = 6;
    }

    /**
     * The action performed when the "Avatar" button is pressed.
     * Selects the avatar enemy tool.
     */
    public void avatarButtonPressed(){
        selectedTile = 7;
    }

}
