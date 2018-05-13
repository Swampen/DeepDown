package deepDown.controllers;


import deepDown.Loader;
import deepDown.level.Animation;
import deepDown.level.GameBoard;
import deepDown.Sound;
import deepDown.gameObjects.*;
import deepDown.gameObjects.enemy.Enemy;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * @author Michael Mobæk Thoresen and Ole-Martin Heggen
 */
public class LevelController {

    private int levelProgression;
    @FXML private Canvas canvas;
    @FXML private AnchorPane anchor;
    @FXML private Text scoreText;
    @FXML private Text coinText;
    @FXML private Text livesText;
    @FXML private Text timeText;
    @FXML private Text levelProgressionText;
    @FXML private Text deathText;
    @FXML private Label fpsLabel;
    @FXML private ImageView coinScoreImage;

    private GraphicsContext gc;
    private double timeScore;
    private long lastCurrentTime = System.nanoTime();
    private int coinCount;
    private int totScore;
    private int avatarLives;
    private boolean endScreen = false;

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
     * Constructor.
     * @param levelProgression Tells it what level it has to load.
     * @param totScore Tells it what score the player has achieved so far.
     * @param avatarLives Tells it how many lives the player has left.
     */
    public LevelController(int levelProgression, int totScore, int avatarLives){
        this.levelProgression = levelProgression;
        this.totScore = totScore;
        this.avatarLives = avatarLives;
    }

    /**
     * Method which runs when the fxml is loaded.
     * Initializes the level by getting all of the objects needed from the GameBoard class
     * and starts the animationTimer.
     */
    @FXML
    private void initialize(){
        double enemyVel = 100;
        timeScore = 200;
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

        anchor.getStylesheets().add("deepDown/resource/stylesheet.css");
        scoreText.setText("Score: " + Integer.toString(totScore));
        livesText.setText("Lives: " + Integer.toString(avatarLives));
        levelProgressionText.setText("Level: " + Integer.toString(levelProgression));

        Image coinscore = new Image("deepDown/resource/images/Coin.png");
        coinScoreImage.setImage(coinscore);

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
                animationTimer.stop();
                if (levelProgression < 9){
                    Loader.loadPauseMenu(anchor, levelProgression, totScore, avatarLives);

                }else {
                    Loader.loadPauseMenuCustomLevel(anchor, levelProgression, totScore, avatarLives);

                }
                resetKeypresses();
                lastCurrentTime = System.nanoTime();
                animationTimer.start();
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
                double deltaTime = (currentTime - lastCurrentTime) / 1000000000.0;
                lastCurrentTime = currentTime;

                coinText.setText(": " + Integer.toString(coinCount));
                timeText.setText("Time: " + Integer.toString((int)timeScore));
                timeScore = timeScore - deltaTime;

                update(deltaTime);
                collisionDetection(deltaTime);
                drawFrame();
            }
        };
        animationTimer.start();
    }

    /**
     * Updates all DynamicGameObjects positions and velocities.
     * @param deltaTime the time since last frame.
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
     * Checks for collision between different GameObjects.
     * @param deltaTime the time since last frame.
     */
    private void collisionDetection(double deltaTime) {

        for (Wall wall : walls) {
            if(avatar.isColliding(wall)){
                double heightOverlap = avatar.intersection(wall).getHeight();
                double widthOverlap = avatar.intersection(wall).getWidth();

                if (widthOverlap > heightOverlap){
                    avatar.reverseVelo();
                    avatar.posUpdate(deltaTime);
                    avatar.setMoveUp(false);
                    avatar.setMoveDown(false);
                }
                if (widthOverlap < heightOverlap){
                    avatar.reverseVelo();
                    avatar.posUpdate(deltaTime);
                    avatar.setMoveLeft(false);
                    avatar.setMoveRight(false);
                }
            }

            for (Enemy enemy : enemies){
                if (avatar.isColliding(enemy)){
                    if (avatarLives == 0){
                        totScore += (coinCount*100);
                        setEndScreen(false);
                    }
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
                Sound.playCoinMedia();
                ++coinCount;
                coins.remove(i);
            }
        }

        if (avatar.isColliding(key) && !key.isPickedUp() ){
            Sound.playDoorMedia();
            key.setPickedUp(true);
            door.setOpen(true);
            door.getSprite().changeSprite(160,40);
        }

        if (avatar.isColliding(door)) {
            if (door.isOpen()){
                if(levelProgression < 8){
                    if(avatarLives < 5){
                        ++avatarLives;
                    }
                    totScore += (coinCount * 100 + timeScore*10);
                    loadNextLevel();
                }else if (levelProgression == 9){
                    animationTimer.stop();
                    Loader.loadLevelEditor(anchor);
                }
                else{
                    totScore += (coinCount*100 + timeScore*10);
                    setEndScreen(true);
                }
            }
        }
    }

    /**
     * Draws all of the objects onto the {@code Canvas}.
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
     * Resets all of the keypress booleans to false.
     */
    private void resetKeypresses(){
        escapePressed.set(false);
        upPressed.set(false);
        downPressed.set(false);
        leftPressed.set(false);
        rightPressed.set(false);
    }

    /**
     * Loads the next level by increasing the levelProgression
     * with one, and resets the level.
     */
    private void loadNextLevel(){
        levelProgression++;
        resetLevel();
    }

    /**
     * Resets the level by stopping the {@code AnimationTimer}
     * and initialize the level again.
     */
    private void resetLevel() {
        lastCurrentTime = System.nanoTime();
        animationTimer.stop();
        initialize();
    }

    /**
     * Retracts one life from avatarLives.
     * If the avatar has more lives remaining,
     * a death sound and animation plays,
     * then the level resets.
     */
    private void killAvatar() {
        --avatarLives;
        if (avatarLives > 0){
            Sound.playEnemyMedia();
            animationTimer.start();
            Animation.deathAnimation(deathText);
            animationTimer.start();
            resetLevel();
        }
    }

    /**
     * Loads the FXML for the end screen and stops the {@code AnimationTimer}.
     * @param gameCompleted Defines if the player completed all levels.
     */
    private void setEndScreen(boolean gameCompleted) {
        animationTimer.stop();
        Loader.loadEndScreen(anchor, totScore, gameCompleted);
    }
}