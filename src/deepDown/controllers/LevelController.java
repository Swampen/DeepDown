package deepDown.controllers;

import deepDown.Main;
import deepDown.gameObjects.*;
import deepDown.gameObjects.Enemy.Enemy;
import deepDown.gameObjects.Enemy.HorisontalEnemy;
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
    private GraphicsContext gc;
    private Main main = new Main();
    private double timeScore = 2000;
    private long lastCurrentTime = System.nanoTime();
    private double enemyVel = 100;
    private int coinCount;
    private int totScore;
    private int avatarLives;

    final BooleanProperty upPressed = new SimpleBooleanProperty(false);
    final BooleanProperty downPressed = new SimpleBooleanProperty(false);
    final BooleanProperty leftPressed = new SimpleBooleanProperty(false);
    final BooleanProperty rightPressed = new SimpleBooleanProperty(false);
    final BooleanProperty escapePressed = new SimpleBooleanProperty(false);

    private Avatar avatar;
    private Key key;
    private Door door;
    private VerticalEnemy vEnemy;
    private HorisontalEnemy hEnemy;

    private Sprite avatarSprite;
    private Sprite keySprite;
    private Sprite doorSprite;

    private ArrayList<Sprite> wallSprites;
    private ArrayList<Sprite> vEnemySprites;
    private ArrayList<Sprite> hEnemySprites;
    private ArrayList<Sprite> coinSprites;

    private AnimationTimer animationTimer;

    public LevelController(int levelProgression, int totScore, int avatarLives){
        this.levelProgression = levelProgression;
        this.totScore = totScore;
        this.avatarLives = avatarLives;
    }

    @FXML
    public void initialize(){

        gc = canvas.getGraphicsContext2D();
        GameBoard level = new GameBoard(levelProgression);
        level.iniitalizeGameBoard(gc);

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

        //Enables key presses in Canvas
        canvas.setFocusTraversable(true);

        //Detects KeyPresses in Canvas
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

        //Detects KeyReleases in Canvas
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


        //Starting Animationtimer
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

    //Updates all of DynamicGameObjects
    private void update(double deltaTime) {
        //Sets avatar velocity on keypresses
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
        avatar.posUpdate(deltaTime);                                //Updates avatar position depending on time since last frame
        avatar.setMovementState(true);                              //Updates the movement state back to true

        for (int j = 0; j < hEnemySprites.size(); j++){
            Sprite hEnemySprite = hEnemySprites.get(j);
            HorisontalEnemy enemy = (HorisontalEnemy)hEnemySprite.getGo();
            enemy.posUpdate(deltaTime);
        }
        for (int j = 0; j < vEnemySprites.size(); j++){
            Sprite vEnemySprite = vEnemySprites.get(j);
            VerticalEnemy enemy = (VerticalEnemy)vEnemySprite.getGo();
            enemy.posUpdate(deltaTime);
        }
    }

    //Detects isColliding
    private void collisionDetection(double deltaTime) {
        //Checks for isColliding with walls
        for (Sprite wallSprite : wallSprites) {
            Wall wall = (Wall)wallSprite.getGo();
            if(avatar.isColliding(wall)){
                //TODO fixing collition

                double heightOverlap = avatar.intersection(wall).getHeight();
                double widthOverlap = avatar.intersection(wall).getWidth();

                //System.out.println("Height: " + heightOverlap);
                //System.out.println("Width : " + widthOverlap + "\n");

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

            //Goes through the ArrayList with Horisontal Enemy sprites
            for (Sprite hEnemySprite : hEnemySprites){
                HorisontalEnemy hEnemy = (HorisontalEnemy) hEnemySprite.getGo();
                //If the avatar collides with an Horisontal enemy

                if (avatar.isColliding(hEnemy)){
                    killAvatar();
                }
                //If a Horisontal enemy collides with a wall
                if (hEnemy.isColliding(wall)){
                    hEnemy.revertXPos();
                    hEnemy.reverseVelo();
                }
            }
            //Goes through the ArrayList with Vertical Enemy sprites
            for (Sprite vEnemySprite : vEnemySprites){
                VerticalEnemy vEnemy = (VerticalEnemy) vEnemySprite.getGo();
                //If the Avatar collides with an Vertical Enemy
                if (avatar.isColliding(vEnemy)){
                    killAvatar();
                }
                //If a Vertical Enemy collides with a Wall
                if (vEnemy.isColliding(wall)){
                    vEnemy.revertYPos();
                    vEnemy.reverseVelo();
                }
            }
        }
        //Checks for avatar isColliding with coins
        for (int i = 0; i < coinSprites.size(); i++){
            if(avatar.isColliding(coinSprites.get(i).getGo())) {
                System.out.println("DING! you got a coin!");
                ++coinCount;
                coinSprites.remove(i);
            }
        }
        //Checks for avatar collition with key
        if (avatar.isColliding(key) && !key.isPickedUp() ){
            System.out.println("Picked up key");
            key.setPickedUp(true);
            door.setOpen(true);
            doorSprite.changeSprite(160,40);
        }
        //Checks for avatar collition with door
        if (avatar.isColliding(door)) {
            if (!door.isOpen()){
                System.out.println("Find the key");
            }else{
                totScore += (coinCount * 100 + timeScore);
                loadNextLevel();        //Loads the next level
            }
        }
    }

    //Draws the entire frame
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

    //Draws ArrayLists on the frame
    public void drawArrayOnFrame(ArrayList<Sprite> sprites){
        for (int i = 0; i < sprites.size(); i++){
            sprites.get(i).render(gc);
        }
    }

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

    private void resetKeypresses() {
        escapePressed.set(false);
        upPressed.set(false);
        downPressed.set(false);
        leftPressed.set(false);
        rightPressed.set(false);
    }

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

    private void resetLevel() {
        lastCurrentTime = System.nanoTime();
        initialize();
    }

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