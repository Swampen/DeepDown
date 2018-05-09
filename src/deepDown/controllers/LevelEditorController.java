package deepDown.controllers;

import deepDown.LevelEditor;
import deepDown.Limit;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class LevelEditorController {

    @FXML private Canvas editorCanvas;
    @FXML private AnchorPane anchor;

    private GraphicsContext gc;
    private double xMouseClicked;
    private double yMouseClicked;
    private AnimationTimer animationTimer;
    private Image image;
    private int[][] levelArray;
    private int selectedTile;

    private boolean avatarLimit = false;
    private boolean doorLimit = false;
    private boolean keyLimit = false;
    private final BooleanProperty mouseClicked = new SimpleBooleanProperty(false);
    private final BooleanProperty mouseDragged = new SimpleBooleanProperty(false);

    private LevelEditor levelEditor;

    @FXML
    public void initialize(){
        gc = editorCanvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, editorCanvas.getWidth(), editorCanvas.getHeight());
        image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/DeepDownTileSet.png"));
        levelEditor = new LevelEditor(image, gc);

        levelArray = levelEditor.getLevelArray();

        editorCanvas.setOnMouseDragged(e -> {
            if (selectedTile == 1 || selectedTile == 2) {
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

        update();
        animationTimer.start();
    }

    private void update() {
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

                    if (Limit.isKeyLimit() && levelArray[levelArrayY][levelArrayX] != 5 && selectedTile != 5) {
                        Limit.setKeyLimit(false);
                    }
                    if (Limit.isDoorLimit() && levelArray[levelArrayY][levelArrayX] != 6 && selectedTile != 6) {
                        Limit.setDoorLimit(false);
                    }
                    if (Limit.isAvatarLimit() && levelArray[levelArrayY][levelArrayX] != 7 && selectedTile != 7) {
                        Limit.setAvatarLimit(false);
                    }

                    if (Limit.isKeyLimit() && selectedTile == 5) {
                        System.out.println("Key already exist");
                    }
                    else if (Limit.isDoorLimit() && selectedTile == 6) {
                        System.out.println("Door already exist");
                    }
                    else if (Limit.isAvatarLimit() && selectedTile == 7){
                        System.out.println("Avatar already exist");
                    }
                    else {
                        levelArray[levelArrayY][levelArrayX] = selectedTile;
                    }




                levelEditor.updateArray(levelArray);
                }
            }
        };
    }

    public void saveLevelPressed(){

    }

    public void loadLevelPressed(){
        //todo load the saved file from the /Files folder
    }

    public void playLevelPressed(){
        //todo start the level created
    }

    public void backToMenuPressed(){
        //todo goes back to menu
    }

    public void discardPressed(){
        levelArray = levelEditor.getLevelArray();
    }

    public void wallButtonPressed(){
        selectedTile = 1;
        System.out.println("wall: " + selectedTile);
    }

    public void coinButtonPressed(){
        selectedTile = 2;
        System.out.println("coin: " + selectedTile);
    }

    public void horizontalEnemyButtonPressed(){
        selectedTile = 3;
        System.out.println("enemy: " + selectedTile);
    }

    public void verticalEnemyButtonPressed(){
        selectedTile = 4;
        System.out.println("enemy: " + selectedTile);
    }

    public void keyButtonPressed(){
        selectedTile = 5;
        System.out.println("key: " + selectedTile);
    }

    public void doorButtonPressed(){
        selectedTile = 6;
        System.out.println("door: " + selectedTile);
    }

    public void avatarButtonPressed(){
        selectedTile = 7;
        System.out.println("avatar: " + selectedTile);
    }
}
