package deepDown;

import deepDown.gameObjects.*;
import deepDown.gameObjects.Enemy.Enemy;
import deepDown.gameObjects.Enemy.HorisontalEnemy;
import deepDown.gameObjects.Enemy.VerticalEnemy;
import deepDown.menuControllers.PauseMenuController;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class LevelController {

    private int selectedLevel;
    private Stage stage;
    @FXML
    private Canvas canvas;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Label scoreLabel;
    private GraphicsContext gc;
    private Main main = new Main();
    private int score = 2000;
    private long lastCurrentTime = System.nanoTime();
    private double enemyVel = 100;

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

    public LevelController(int selectedLevel){
        this.selectedLevel = selectedLevel;
    }

    @FXML
    public void initialize(){

        gc = canvas.getGraphicsContext2D();
        GameBoard level = new GameBoard(selectedLevel);
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

        //Counting down score by one every second
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                score--;
            }
        }, 0, 1000);

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
                scoreLabel.setText(Integer.toString(score));                        //Updates score

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

    private void closeWindow() {
        animationTimer.stop();
        System.out.println("closing");
        stage.close();
    }

    private void showPauseMenu() {
        PauseMenuController controller = new PauseMenuController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/pauseMenu.fxml"));
        loader.setController(controller);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);
        stage.setScene(new Scene(root));
        stage.showAndWait();
        escapePressed.set(false);
        animationTimer.start();
    }

    //Detects collision
    private void collisionDetection(double deltaTime) {
        //Checks for collision with walls
        for (int i = 0; i < wallSprites.size(); i++) {

            if(avatar.collision(wallSprites.get(i).getGo())){
                if(avatar.getXVelo() < 0) {
                    avatar.setXVelo(0);
                    avatar.setXPos();
                    avatar.setCanMoveLeft(false);
                }
                if(avatar.getXVelo() > 0) {
                    avatar.setXVelo(0);
                    avatar.setXPos();
                    avatar.setCanMoveRight(false);
                }
                if (avatar.getYVelo() < 0) {
                    avatar.setYVelo(0);
                    avatar.setYPos();
                    avatar.setCanMoveUp(false);
                }
                if (avatar.getYVelo() > 0){
                    avatar.setYVelo(0);
                    avatar.setYPos();
                    avatar.setCanMoveDown(false);
                }
                break;
            }

            //Goes through the ArrayList with Horisontal Enemy sprites
            for (int j = 0; j < hEnemySprites.size(); j++){
                Sprite hEnemySprite = hEnemySprites.get(j);
                HorisontalEnemy hEnemy = (HorisontalEnemy) hEnemySprite.getGo();
                //If the avatar collides with an Horisontal enemy
                if (avatar.collision(hEnemy)){
                    resetLevel();
                }
                //If a Horisontal enemy collides with a wall
                if (hEnemy.collision(wallSprites.get(i).getGo())){
                    hEnemy.setXPos();
                    hEnemy.reverseVelo();
                }
            }
            //Goes through the ArrayList with Vertical Enemy sprites
            for (int j = 0; j < vEnemySprites.size(); j++){
                Sprite vEnemySprite =vEnemySprites.get(j);
                VerticalEnemy vEnemy = (VerticalEnemy) vEnemySprite.getGo();

                //If the Avatar collides with an Vertical Enemy
                if (avatar.collision(vEnemy)){
                    resetLevel();
                }
                //If a Vertical Enemy collides with a Wall
                if (vEnemy.collision(wallSprites.get(i).getGo())){
                    vEnemy.setYPos();
                    vEnemy.reverseVelo();
                }
            }
        }
        //Checks for avatar collision with coins
        for (int i = 0; i < coinSprites.size(); i++){
            if(avatar.collision(coinSprites.get(i).getGo())) {
                System.out.println("DING! you got a coin!");
                coinSprites.remove(i);
            }
        }
        //Checks for avatar collition with key
        if (avatar.collision(key) && !key.isPickedUp() ){
            System.out.println("Picked up key");
            key.setPickedUp(true);
            door.setOpen(true);
            doorSprite.changeSprite(160,40);
        }
        //Checks for avatar collition with door
        if (avatar.collision(door)) {
            if (!door.isOpen()){
                System.out.println("Find the key");
            }else{
                loadNextLevel();        //Loads the next level
            }
        }
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

    private void loadNextLevel() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/level.fxml"));
        LevelController controller = new LevelController(selectedLevel+1);
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
        avatarSprite.renderAvatar(gc);
    }

    //Draws ArrayLists on the frame
    public void drawArrayOnFrame(ArrayList<Sprite> sprites){
        for (int i = 0; i < sprites.size(); i++){
            sprites.get(i).render(gc);
        }
    }
}