package deepDown.controllers;

import deepDown.Main;
import deepDown.gameObjects.*;
import deepDown.gameObjects.Enemy.Enemy;
import deepDown.gameObjects.Enemy.HorizontalEnemy;
import deepDown.gameObjects.Enemy.VerticalEnemy;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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
     * timeScore = varible for keeping track of the Time Score of the player
     * set to the initial 2000
     * lastCurrentTime = varible that gets time from the system clock and uses it for calculating
     * time difference
     * enemyVel =  The velocity value for the all enemies on the board
     * coinCount = Variable for keeping track of how many coins the player has collected during a level
     * totScore = Variable for keeping track of the total score the player has achieved throughout the
     * entire game
     * avatarLives = varible for keeping track of how many lives the player has
     */
    private GraphicsContext gc;
    private Main main = new Main();
    private double timeScore = 2000;
    private long lastCurrentTime = System.nanoTime();
    private double enemyVel = 100;
    private int coinCount;
    private int totScore;
    private int avatarLives;

    /**
     * Booleans for keeping track of which keys are being pressed
     */
    final BooleanProperty upPressed = new SimpleBooleanProperty(false);
    final BooleanProperty downPressed = new SimpleBooleanProperty(false);
    final BooleanProperty leftPressed = new SimpleBooleanProperty(false);
    final BooleanProperty rightPressed = new SimpleBooleanProperty(false);
    final BooleanProperty escapePressed = new SimpleBooleanProperty(false);

    private Avatar avatar;
    private Key key;
    private Door door;
    private VerticalEnemy vEnemy;
    private HorizontalEnemy hEnemy;

    private Sprite avatarSprite;
    private Sprite keySprite;
    private Sprite doorSprite;

    private ArrayList<Sprite> wallSprites;
    private ArrayList<Sprite> vEnemySprites;
    private ArrayList<Sprite> hEnemySprites;
    private ArrayList<Sprite> coinSprites;

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
    public void initialize(){

        gc = canvas.getGraphicsContext2D();
        GameBoard level = new GameBoard(levelProgression);
        level.initializeGameBoard(gc);

        avatar = level.getAvatar();
        key = level.getKey();
        door = level.getDoor();
        vEnemy = level.getVEnemy();
        hEnemy = level.getHEnemy();

        avatarSprite = level.getAvatarSprite();
        keySprite = level.getKeySprite();
        doorSprite = level.getDoorSprite();

        wallSprites = level.getWallSprites();
        vEnemySprites = level.getVEnemieSprites();
        hEnemySprites = level.getHEnemieSprites();
        coinSprites = level.getCoinSprites();
        coinCount = 0;
        System.out.println(totScore);

        /**
         * Enables keypresses in canvas
         */
        canvas.setFocusTraversable(true);

        /**
         * Detects when keys are being pressed and sets their respective booleans to true
         */
        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e){
                if (e.getCode() == KeyCode.UP ){
                    upPressed.set(true);
                }if (e.getCode() == KeyCode.DOWN){
                    downPressed.set(true);
                }if (e.getCode() == KeyCode.LEFT){
                    leftPressed.set(true);
                }if (e.getCode() == KeyCode.RIGHT){
                    rightPressed.set(true);
                }if (e.getCode() == KeyCode.ESCAPE){
                    escapePressed.set(true);
                    showPauseMenu();
                }
            }
        });

        /**
         * Detects when keys are being released and sets their respective booleans to false
         */
        canvas.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.UP){
                    upPressed.set(false);
                }if (e.getCode() == KeyCode.DOWN){
                    downPressed.set(false);
                }if (e.getCode() == KeyCode.LEFT){
                    leftPressed.set(false);
                }if (e.getCode() == KeyCode.RIGHT){
                    rightPressed.set(false);
                }
            }
        });

        for (int j = 0; j < hEnemySprites.size(); j++) {
            Sprite hEnemySprite = hEnemySprites.get(j);
            Enemy enemy = (Enemy) hEnemySprite.getGo();
            enemy.setXVelo(enemyVel);
        }

        for (int j = 0; j < vEnemySprites.size(); j++){
            Sprite vEnemySprite = vEnemySprites.get(j);
            VerticalEnemy enemy = (VerticalEnemy)vEnemySprite.getGo();
            enemy.setYVelo(enemyVel);
        }


        /**
         * Starts the Animation Timer responsible for updating Labels and Objects
         */
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

        for (int j = 0; j < hEnemySprites.size(); j++){
            Sprite hEnemySprite = hEnemySprites.get(j);
            HorizontalEnemy enemy = (HorizontalEnemy)hEnemySprite.getGo();
            enemy.posUpdate(deltaTime);
        }
        for (int j = 0; j < vEnemySprites.size(); j++){
            Sprite vEnemySprite = vEnemySprites.get(j);
            VerticalEnemy enemy = (VerticalEnemy)vEnemySprite.getGo();
            enemy.posUpdate(deltaTime);
        }
    }

    /**
     * Checks for collision between different objects by cycling through array lists of objects and comparing their hitbox
     * with other objects' hitboxes
     * @param deltaTime the difference in time between now and the last time the animationTimer ran
     */
    private void collisionDetection(double deltaTime) {

        /**
         * Checks for collision between the AvatarSprite and WallSprites
         * and stops the AvatarSprites when it detects a collision
         */
        for (Sprite wallSprite : wallSprites) {
            Wall wall = (Wall)wallSprite.getGo();
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
                    avatar.posUpdate(deltaTime);;
                    avatar.setCanMoveLeft(false);
                    avatar.setCanMoveRight(false);

                }
            }

            /**
             * Iterates through the HorizontalEnemySprite array list to check for collisions
             * between HorizontalEnemySprites and WallSprites, and between HorizontalEnemySprites and the AvatarSprite
             * When HorizontalEnemySprites encounter a wall their Velocities gets reversed and when they encounter
             * the AvatarSprites then kill it.
             */
            for (Sprite hEnemySprite : hEnemySprites){
                HorizontalEnemy hEnemy = (HorizontalEnemy) hEnemySprite.getGo();

                if (avatar.isColliding(hEnemy)){
                    killAvatar();
                }

                if (hEnemy.isColliding(wall)){
                    hEnemy.reverseVelo();
                    hEnemy.posUpdate(deltaTime);
                }
            }
            /**
             * Iterates through the VerticalEnemySprite array list to check for collisions
             * between VerticalEnemySprites and WallSprites, and between VerticalEnemySprites and the AvatarSprite
             * When VerticalEnemySprites encounter a wall their Velocities gets reversed and when they encounter
             * the AvatarSprites then kill it.
             */
            for (Sprite vEnemySprite : vEnemySprites){
                VerticalEnemy vEnemy = (VerticalEnemy) vEnemySprite.getGo();

                if (avatar.isColliding(vEnemy)){
                    killAvatar();
                }

                if (vEnemy.isColliding(wall)){
                    vEnemy.reverseVelo();
                    vEnemy.posUpdate(deltaTime);
                }
            }
        }
        /**
         * Iterates through the CoinSprites array list to check for collision between
         * CoinSprites and AvatarSprite
         * When a collsion occures it will remove the coin and increment the coinCount value by 1
         */
        for (int i = 0; i < coinSprites.size(); i++){
            if(avatar.isColliding(coinSprites.get(i).getGo())) {
                System.out.println("DING! you got a coin!");
                ++coinCount;
                coinSprites.remove(i);
            }
        }

        /**
         * Checks for Collision between the KeySprite and the AvatarSprite
         * When the avatar collides with the key it will change the isPickedUp boolean to true
         * and open the door to the next level
         */
        if (avatar.isColliding(key) && !key.isPickedUp() ){
            System.out.println("Picked up key");
            key.setPickedUp(true);
            door.setOpen(true);
            doorSprite.changeSprite(160,40);
        }
        /**
         * Checks for collision between the DoorSprite and the AvatarSprite
         * Then the avatar collides with the door and the avatar has picked up the key
         * it will add the timeScore and their coinCount*100 to the total score and transport them to the next level
         * if not the door will do nothing
         */
        if (avatar.isColliding(door)) {
            if (!door.isOpen()){
                System.out.println("Find the key");
            }else{
                if(avatarLives<5){
                    ++avatarLives;
                }
                totScore += (coinCount * 100 + timeScore);
                loadNextLevel();
            }
        }
    }

    /**
     * Draws all of the objects onto the GameBoard
     */
    public void drawFrame(){
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());      //Clears all of Canvas
        doorSprite.render(gc);
        if (!key.isPickedUp()){
            keySprite.render(gc);
        }
        drawArrayOnFrame(wallSprites);
        drawArrayOnFrame(vEnemySprites);
        drawArrayOnFrame(hEnemySprites);
        drawArrayOnFrame(coinSprites);
        avatarSprite.render(gc);
    }

    /**
     * Method for drwaing objects of a certain type onto the GameBoard
     * @param sprites The type of sprite you want drawn onto the GameBoard
     */
    public void drawArrayOnFrame(ArrayList<Sprite> sprites){
        for (int i = 0; i < sprites.size(); i++){
            sprites.get(i).render(gc);
        }
    }

    /**
     * Shows the Pause Menu when you hit the Escape key
     */
    private void showPauseMenu() {

        Stage stage = new Stage();
        PauseMenuController controller = new PauseMenuController(stage, anchor, levelProgression, totScore, avatarLives);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/pauseMenu.fxml"));
        loader.setController(controller);
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();
        resetKeypresses();
        lastCurrentTime = System.nanoTime();
        animationTimer.start();
    }

    /**
     * Resets all of the keypress booleans to false
     */
    private void resetKeypresses() {
        escapePressed.set(false);
        upPressed.set(false);
        downPressed.set(false);
        leftPressed.set(false);
        rightPressed.set(false);
    }

    /**
     * Loads the next level by making a new loader and LevelController object and loading those
     */
    private void loadNextLevel() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/level.fxml"));
        LevelController controller = new LevelController(levelProgression +1, totScore, avatarLives);
        loader.setController(controller);
        Parent root = main.getRoot();

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        anchor.getChildren().setAll(root);
        animationTimer.stop();
        resetLevel();
    }

    /**
     * Resets the level to it's initial state
     */
    private void resetLevel() {
        lastCurrentTime = System.nanoTime();
        initialize();
    }

    /**
     * If the avatar has more than zero lives remaining then it resets the level
     * if the avatar has zero lives remaining then displays the Game Over Screen
     */
    public void killAvatar() {
        if(avatarLives > 0) {
            --avatarLives;
            System.out.println(avatarLives);
            resetLevel();
        }else {
            System.out.println("Game Over!");
            System.out.println("Score: " + (totScore +(coinCount*100 + timeScore)));
        }
    }
}