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
    long lastNanoTime = System.nanoTime();

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

        Avatar player = new Avatar(1*40, 16*40, 30, 30, 3, true, 0, 0);



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
                double deltaTime = (currentTime - lastNanoTime ) / 1000000000.0;
                lastNanoTime = currentTime;
                scoreLabel.setText(Integer.toString(score));





                //System.out.println(deltaTime);
                //score -= (deltaTime/100000000000.0);
                //scoreLabel.setText(Integer.toString(score));

                Sprite playerSprite = new Sprite(image, player, Type.AVATAR, 0, 40);
                for (int i = 0; i < sprites.size(); i++) {

                    if(playerSprite.collision(sprites.get(i))){
                        if (sprites.get(i).getType() == Type.WALL){

                            if(player.getXVelo() < 0) {
                                player.setXVelo(0);
                                player.setXPos(player.getPrevX());
                                player.setCanMoveLeft(false);
                            }
                            if(player.getXVelo() > 0) {
                                player.setXVelo(0);
                                player.setXPos(player.getPrevX());
                                player.setCanMoveRight(false);
                            }
                            if (player.getYVelo() < 0) {
                                player.setYVelo(0);
                                player.setYPos(player.getPrevY());
                                player.setCanMoveDown(false);
                            }
                            if (player.getYVelo() > 0){
                                player.setYVelo(0);
                                player.setYPos(player.getPrevY());
                                player.setCanMoveDown(false);
                            }
                        }

                        if (sprites.get(i).getType() == Type.PICKUP){
                            System.out.println("DING! you got a coin!");
                            sprites.remove(sprites.get(i));
                        }
                        if(playerSprite.collision(sprites.get(i)) && sprites.get(i).getType() == Type.KEY){
                            System.out.println("Exit is now open");
                            sprites.remove(sprites.get(i));
                        }
                        if(playerSprite.collision(sprites.get(i)) && sprites.get(i).getType() == Type.EXIT /*&& sprites.contains(keySprite)*/){
                            System.out.println("Find the key");
                        }
                    }
                }

                player.setXVelo(0);
                player.setYVelo(0);
                if (upPressed.getValue() && player.getCanMoveUp()) {
                    player.setYVelo(-200);
                }
                if (downPressed.getValue() && player.getCanMoveDown()) {
                    player.setYVelo(200);
                }
                if (leftPressed.getValue() && player.getCanMoveLeft()) {
                    player.setXVelo(-200);
                }
                if (rightPressed.getValue() && player.getCanMoveRight()) {
                    player.setXVelo(200);
                }
                player.posUpdate(deltaTime);

                player.setMovementState(true);
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                level1.renderSprite(sprites, gc);
                playerSprite.renderPlayer(gc);
            }
        }.start();
    }
}