package deepDown.controllers;


import deepDown.GameBoard;
import deepDown.Leaderboard;
import deepDown.gameObjects.*;
import deepDown.gameObjects.enemy.Enemy;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class LevelController {

    private int levelProgression;
    @FXML
    private Canvas canvas;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label totScoreLabel;
    @FXML
    private Label coinCountLabel;
    @FXML
    private Label livesCounter;
    /**
     * timeScore = variable for keeping track of the Time Score of the player
     * set to the initial 2000
     * lastCurrentTime = variable that gets time from the system clock and uses it for calculating
     * time difference
     * enemyVel =  The velocity value for the all enemies on the board
     * coinCount = Variable for keeping track of how many coins the player has collected during a level
     * totScore = Variable for keeping track of the total score the player has achieved throughout the
     * entire game
     * avatarLives = variable for keeping track of how many lives the player has
     * endScreen = a boolean to determine if the end screen is showing or not
     */
    private GraphicsContext gc;
    private double timeScore = 2000;
    private long lastCurrentTime = System.nanoTime();
    private int coinCount;
    private int totScore;
    private int avatarLives;
    private boolean endScreen = false;

    /**
     * Booleans for keeping track of which keys are being pressed
     */
    private final BooleanProperty upPressed = new SimpleBooleanProperty(false);
    private final BooleanProperty downPressed = new SimpleBooleanProperty(false);
    private final BooleanProperty leftPressed = new SimpleBooleanProperty(false);
    private final BooleanProperty rightPressed = new SimpleBooleanProperty(false);
    private final BooleanProperty escapePressed = new SimpleBooleanProperty(false);

    private Avatar avatar;
    private Key key;
    private Door door;

    private ArrayList<Wall> walls;
    private ArrayList<Coin> coins;
    private ArrayList<Enemy> enemies;

    private AnimationTimer animationTimer;

    /**
     * Constructor for the LevelController class
     * @param levelProgression Tells it what level it has to load
     * @param totScore Tells it what score the player has achieved so far
     * @param avatarLives Tells it how many lives the player has left
     */
    public LevelController(int levelProgression, int totScore, int avatarLives){
        this.levelProgression = levelProgression;
        this.totScore = totScore;
        this.avatarLives = avatarLives;
    }

    /**
     * Initializes the level by getting all of the objects needed from the GameBoard class
     * and starts the animationTimer
     */
    @FXML
    private void initialize(){

        double enemyVel = 100;
        gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        GameBoard level = new GameBoard(levelProgression, enemyVel);
        level.initializeGameBoard();

        avatar = level.getAvatar();
        key = level.getKey();
        door = level.getDoor();

        walls = level.getWalls();
        coins = level.getCoins();
        enemies = level.getEnemies();
        coinCount = 0;

        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                upPressed.set(true);
            }
            if (e.getCode() == KeyCode.DOWN) {
                downPressed.set(true);
            }
            if (e.getCode() == KeyCode.LEFT) {
                leftPressed.set(true);
            }
            if (e.getCode() == KeyCode.RIGHT) {
                rightPressed.set(true);
            }
            if (e.getCode() == KeyCode.ESCAPE && !endScreen) {
                escapePressed.set(true);
                try {
                    showPauseMenu();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (e.getCode() == KeyCode.K){
                try {
                    gameOverScreen();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        canvas.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.UP) {
                upPressed.set(false);
            }
            if (e.getCode() == KeyCode.DOWN) {
                downPressed.set(false);
            }
            if (e.getCode() == KeyCode.LEFT) {
                leftPressed.set(false);
            }
            if (e.getCode() == KeyCode.RIGHT) {
                rightPressed.set(false);
            }
        });

        animationTimer = new AnimationTimer(){
            @Override
            public void handle(long currentTime){
                double deltaTime = (currentTime - lastCurrentTime) / 1000000000.0;  //Time since last frame
                lastCurrentTime = currentTime;                                      //Saves the time in current frame

                scoreLabel.setText("Time: " + Integer.toString((int)timeScore));     //Updates timeScore
                coinCountLabel.setText("Coins: " + Integer.toString(coinCount)); //Updates coinCount
                totScoreLabel.setText("Score: " + Integer.toString(totScore));  //Updates totScore
                livesCounter.setText("Lives: " + Integer.toString(avatarLives)); //Updates livesCounter
                timeScore = timeScore - deltaTime;

                if (avatarLives == 0){
                    try {
                        gameOverScreen();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                update(deltaTime);
                collisionDetection(deltaTime);
                drawFrame();

                if (escapePressed.get()){
                    stop();
                }
            }
        };
        animationTimer.start();
    }

    /**
     * Updates all DynamicGameObjects positions and velocities using the animationTimer
     * @param deltaTime the difference in time between now and the last time the animationTimer ran
     */
    private void update(double deltaTime) {

        avatar.setXVelo(0);
        avatar.setYVelo(0);

        if (upPressed.getValue() && avatar.getCanMoveUp()) {
            avatar.setYVelo(-200);
        }

        if (downPressed.getValue() && avatar.getCanMoveDown()) {
            avatar.setYVelo(200);
        }

        if (leftPressed.getValue() && avatar.getCanMoveLeft()) {
            avatar.setXVelo(-200);
        }

        if (rightPressed.getValue() && avatar.getCanMoveRight()) {
            avatar.setXVelo(200);
        }
        avatar.posUpdate(deltaTime);
        avatar.setMovementState(true);

        for (Enemy enemy : enemies) {
            enemy.posUpdate(deltaTime);
        }
    }

    /**
     * Checks for collision between different objects by cycling through array lists of objects and comparing their boundingbox
     * with other objects' boundingbox
     * @param deltaTime the difference in time between now and the last time the animationTimer ran
     */
    private void collisionDetection(double deltaTime) {

        //Checks for collision between the AvatarSprite and WallSprites
        //and stops the AvatarSprites when it detects a collision
        for (Wall wall : walls) {
            if(avatar.isColliding(wall)){

                double heightOverlap = avatar.intersection(wall).getHeight();
                double widthOverlap = avatar.intersection(wall).getWidth();

                if (widthOverlap > heightOverlap){
                    avatar.reverseVelo();
                    avatar.posUpdate(deltaTime);
                    avatar.setCanMoveUp(false);
                    avatar.setCanMoveDown(false);
                }
                if (widthOverlap < heightOverlap){
                    avatar.reverseVelo();
                    avatar.posUpdate(deltaTime);
                    avatar.setCanMoveLeft(false);
                    avatar.setCanMoveRight(false);
                }
            }

            for (Enemy enemy : enemies){
                if (avatar.isColliding(enemy)){
                    killAvatar();
                }
                if (enemy.isColliding(wall)){
                    enemy.reverseVelo();
                    enemy.posUpdate(deltaTime);
                }
            }
        }

        for (int i = 0; i < coins.size(); i++){
            if(avatar.isColliding(coins.get(i))) {
                System.out.println("DING! you got a coin!");
                ++coinCount;
                coins.remove(i);
            }
        }

        if (avatar.isColliding(key) && !key.isPickedUp() ){
            System.out.println("Picked up key");
            key.setPickedUp(true);
            door.setOpen(true);
            door.getSprite().changeSprite(160,40);
        }

        if (avatar.isColliding(door)) {
            if (!door.isOpen()){
                System.out.println("Find the key");
            }else{

                if(levelProgression!=3){

                    if(avatarLives<5){
                        ++avatarLives;
                    }
                    totScore += (coinCount * 100 + timeScore);
                    try {
                        loadNextLevel();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }  else {
                    totScore += (coinCount*100 + timeScore);
                    try{
                        gameOverScreen();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Draws all of the objects onto the GameBoard
     */
    private void drawFrame(){
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());      //Clears all of Canvas
        door.getSprite().render(gc, door);
        if (!key.isPickedUp()){
            key.getSprite().render(gc, key);
        }
        for (Wall wall : walls){
            wall.getSprite().render(gc, wall);
        }
        for (Coin coin : coins){
            coin.getSprite().render(gc, coin);
        }
        for (Enemy enemy : enemies){
            enemy.getSprite().render(gc, enemy);
        }
        avatar.getSprite().render(gc, avatar);
    }

    /**
     * Shows the Pause Menu when you hit the Escape key
     */
    private void showPauseMenu() throws IOException{
        Rectangle r = new Rectangle(0, 0, canvas.getWidth(), canvas.getHeight());
        r.setFill(Color.rgb(0, 0, 0, 0.6));
        anchor.getChildren().add(r);

        Stage stage = new Stage();
        PauseMenuController controller = new PauseMenuController(stage, anchor, levelProgression, totScore, avatarLives);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/pauseMenu.fxml"));
        loader.setController(controller);
        Parent root = loader.load();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();
        anchor.getChildren().remove(r);
        resetKeypresses();
        lastCurrentTime = System.nanoTime();
        animationTimer.start();
    }

    /**
     * Resets all of the keypress booleans to false
     */
    private void resetKeypresses(){
        escapePressed.set(false);
        upPressed.set(false);
        downPressed.set(false);
        leftPressed.set(false);
        rightPressed.set(false);
    }

    /**
     * Loads the next level by making a new loader and LevelController object and loading those
     */
    private void loadNextLevel() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/level.fxml"));
        LevelController controller = new LevelController(levelProgression +1, totScore, avatarLives);
        loader.setController(controller);
        Parent root = loader.load();
        anchor.getChildren().setAll(root);
        resetLevel();
    }

    /**
     * Resets the level to it's initial state
     */
    private void resetLevel() {
        lastCurrentTime = System.nanoTime();
        animationTimer.stop();
        initialize();
    }

    /**
     * If the avatar has more than zero lives remaining then it resets the level
     * if the avatar has zero lives remaining then displays the Game Over Screen
     */
    private void killAvatar() {
        if(avatarLives > 0) {
            --avatarLives;
            resetLevel();
        }else {
            totScore += (coinCount*100);
            try{
                gameOverScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void gameOverScreen()throws IOException{
        endScreen = true;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/gameOverScreen.fxml"));
        EndScreenController endScreen = new EndScreenController(totScore);
        loader.setController(endScreen);
        Parent root = loader.load();

        anchor.getChildren().setAll(root);
        animationTimer.stop();

    }
}