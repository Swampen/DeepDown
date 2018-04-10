package deepDown;

import deepDown.gameObjects.Avatar;
import deepDown.gameObjects.GameBoard;
import deepDown.gameObjects.Sprite;
import deepDown.gameObjects.Type;
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
    protected Canvas canvas;
    @FXML
    protected AnchorPane anchor;
    protected GraphicsContext gc;
    @FXML
    protected Label scoreLabel;
    private int score = 2000;
    private Image image;
    long lastCurrentTime = System.nanoTime();

    final BooleanProperty upPressed = new SimpleBooleanProperty(false);
    final BooleanProperty downPressed = new SimpleBooleanProperty(false);
    final BooleanProperty leftPressed = new SimpleBooleanProperty(false);
    final BooleanProperty rightPressed = new SimpleBooleanProperty(false);

    public LevelController(int level){
        this.level = level;
    }

    @FXML
    public void initialize() throws IOException{
        gc = canvas.getGraphicsContext2D();
        GameBoard level1 = new GameBoard(this.level);
        ArrayList<Sprite> sprites;
        sprites = level1.drawBoard(gc);
        image = new Image(new FileInputStream("src/deepDown/resource/DeepDownTileSet.png"));

        Avatar avatar = new Avatar(1*40, 16*40, 30, 30, 3, true, 0, 0);

        ArrayList<String> input = new ArrayList<String>();
        canvas.setFocusTraversable(true);
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

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                score--;
            }
        }, 0, 1000);

        new AnimationTimer(){
            public void handle(long currentTime){
                double deltaTime = (currentTime - lastCurrentTime) / 1000000000.0;
                lastCurrentTime = currentTime;
                scoreLabel.setText(Integer.toString(score));

                //System.out.println(deltaTime);
                //score -= (deltaTime/100000000000.0);
                //scoreLabel.setText(Integer.toString(score));

                Sprite avatarSprite = new Sprite(image, avatar, Type.AVATAR, 0, 40);
                for (int i = 0; i < sprites.size(); i++) {

                    if(avatarSprite.collision(sprites.get(i))){
                        if (sprites.get(i).getType() == Type.WALL){

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

                        if (sprites.get(i).getType() == Type.COIN){
                            System.out.println("DING! you got a coin!");
                            sprites.remove(sprites.get(i));
                        }
                        if(avatarSprite.collision(sprites.get(i)) && sprites.get(i).getType() == Type.KEY){
                            System.out.println("Exit is now open");
                            sprites.remove(sprites.get(i));
                        }
                        if(avatarSprite.collision(sprites.get(i)) && sprites.get(i).getType() == Type.DOOR /*&& sprites.contains(keySprite)*/){
                            System.out.println("Find the key");
                        }
                    }
                }

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
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                level1.renderSprite(sprites, gc);
                avatarSprite.renderAvatar(gc);
            }
        }.start();
    }
}