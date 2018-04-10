package deepDown;

import deepDown.gameObjects.*;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class LevelController {

    private int level;
    @FXML
    private Canvas canvas;
    @FXML
    private AnchorPane anchor;
    private GraphicsContext gc;
    @FXML
    private Label scoreLabel;
    private int score = 2000;
    private Image image;
    long lastCurrentTime = System.nanoTime();
    double enemyVel = 5;

    final BooleanProperty upPressed = new SimpleBooleanProperty(false);
    final BooleanProperty downPressed = new SimpleBooleanProperty(false);
    final BooleanProperty leftPressed = new SimpleBooleanProperty(false);
    final BooleanProperty rightPressed = new SimpleBooleanProperty(false);

    private Avatar avatar;
    private Key key;
    private Door door;

    private Sprite avatarSprite;
    private Sprite keySprite;
    private Sprite doorSprite;

    private ArrayList<Sprite> wallSprites;
    private ArrayList<Sprite> vEnemySprites;
    private ArrayList<Sprite> hEnemySprites;
    private ArrayList<Sprite> coinSprites ;

    public LevelController(int level){
        this.level = level;
    }

    @FXML
    public void initialize() throws IOException{

        gc = canvas.getGraphicsContext2D();
        GameBoard level = new GameBoard(this.level);
        level.iniitalizeGameBoard(gc);

        avatar = level.getAvatar();
        key = level.getKey();
        door = level.getDoor();

        avatarSprite = level.getAvatarSprite();
        keySprite = level.getKeySprite();
        doorSprite = level.getDoorSprite();

        wallSprites = level.getWallSprites();
        vEnemySprites = level.getVEnemieSprites();
        hEnemySprites = level.getHEnemieSprites();
        coinSprites = level.getCoinSprites();



        //Enables keypresses in Canvas
        canvas.setFocusTraversable(true);

        //Detects KeyPresses in Canvas
        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {

                if (e.getCode() == KeyCode.UP ){
                    upPressed.set(true);
                }if (e.getCode() == KeyCode.DOWN){
                    downPressed.set(true);
                }if (e.getCode() == KeyCode.LEFT){
                    leftPressed.set(true);
                }if (e.getCode() == KeyCode.RIGHT){
                    rightPressed.set(true);
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

        //Couning down score by one every second
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                score--;
            }
        }, 0, 1000);


        //Starting Animationtimer
        new AnimationTimer(){
            public void handle(long currentTime){
                double deltaTime = (currentTime - lastCurrentTime) / 1000000000.0;  //Time since last frame
                lastCurrentTime = currentTime;                                      //Saves the time in current frame
                scoreLabel.setText(Integer.toString(score));                        //Updates score

                for (int j = 0; j < hEnemySprites.size(); j++){
                    Sprite hEnemySprite = hEnemySprites.get(j);
                    hEnemySprite.getGo().setX(enemyVel);
                }
                /*for (int j = 0; j < vEnemySprites.size(); j++){
                    Sprite vEnemySprite = vEnemySprites.get(j);
                    vEnemySprite.getGo().setY(enemyVel);
                }*/

                //Checks for collition with walls
                for (int i = 0; i < wallSprites.size(); i++) {

                    if(avatarSprite.collision(wallSprites.get(i))){
                        if(avatar.getXVelo() < 0) {
                            avatar.setXVelo(0);
                            avatar.setXPos(avatar.getPrevX());
                            avatar.setCanMoveLeft(false);
                        }
                        if(avatar.getXVelo() > 0) {
                            avatar.setXVelo(0);
                            avatar.setXPos(avatar.getPrevX());
                            avatar.setCanMoveRight(false);
                        }
                        if (avatar.getYVelo() < 0) {
                            avatar.setYVelo(0);
                            avatar.setYPos(avatar.getPrevY());
                            avatar.setCanMoveDown(false);
                        }
                        if (avatar.getYVelo() > 0){
                            avatar.setYVelo(0);
                            avatar.setYPos(avatar.getPrevY());
                            avatar.setCanMoveDown(false);
                        }
                    }
                    for (int j = 0; j < hEnemySprites.size(); j++){
                        Sprite hEnemySprite = hEnemySprites.get(j);
                        if (hEnemySprite.collision(wallSprites.get(i))){
                            enemyVel = -enemyVel;
                        }
                    }

                    for (int j = 0; j < vEnemySprites.size(); j++){
                        Sprite vEnemySprite = vEnemySprites.get(j);
                        if (vEnemySprite.collision(wallSprites.get(i))){
                            enemyVel = -enemyVel;
                        }
                    }
                }

                //Checks for avatar collition with coins
                for (int i = 0; i < coinSprites.size(); i++){
                    if(avatarSprite.collision(coinSprites.get(i))) {
                        System.out.println("DING! you got a coin!");
                        coinSprites.remove(i);
                    }
                }

                //Checks for avatar collition with key
                if (avatarSprite.collision(keySprite) && !key.isPickedUp() ){
                    System.out.println("Picked up key");
                    key.setPickedUp(true);
                    door.setOpen(true);
                }

                //Checks for avatar collition with door
                if (avatarSprite.collision(doorSprite)) {
                    if (!door.isOpen()){
                        System.out.println("Find the key");
                    }else{
                        System.out.println("Next level");
                    }


                }

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
                avatar.posUpdate(deltaTime);        //Updates avatar position depending on time since last frame

                avatar.setMovementState(true);
                //level1.renderSprite(sprites, gc);
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());      //Clears all of Canvas
                drawFrame();
            }
        }.start();
    }

    //Draws the entire frame
    public void drawFrame(){
        avatarSprite.renderAvatar(gc);
        doorSprite.render(gc);

        if (!key.isPickedUp()){
            keySprite.render(gc);
        }

        drawArrayOnFrame(wallSprites);
        drawArrayOnFrame(vEnemySprites);
        drawArrayOnFrame(hEnemySprites);
        drawArrayOnFrame(coinSprites);
    }

    //Draws ArrayLists on the frame
    public void drawArrayOnFrame(ArrayList<Sprite> sprites){
        for (int i = 0; i < sprites.size(); i++){
            sprites.get(i).render(gc);
        }
    }
}