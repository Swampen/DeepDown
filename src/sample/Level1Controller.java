package sample;

import com.sun.jdi.LongValue;
import com.sun.jdi.Type;
import com.sun.jdi.VirtualMachine;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Level1Controller {

    @FXML
    protected Canvas canvas;
    @FXML
    protected AnchorPane anchor;
    protected GraphicsContext gc;



    final BooleanProperty upPressed = new SimpleBooleanProperty(false);
    final BooleanProperty downPressed = new SimpleBooleanProperty(false);
    final BooleanProperty leftPressed = new SimpleBooleanProperty(false);
    final BooleanProperty rightPressed = new SimpleBooleanProperty(false);

    @FXML
    public void initialize() throws IOException{
        gc = canvas.getGraphicsContext2D();
        GameBoard level1 = new GameBoard(1);
        Image image;
        ArrayList<Sprite> sprites;
        sprites = level1.drawBoard(gc);
        image = new Image(new FileInputStream("src/sample/DeepDownTileSet.png"));
        /*try {
            sprites = level1.drawBoard(gc);
            image = new Image(new FileInputStream("src/sample/DeepDownTileSet.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        Avatar player = new Avatar(1*40, 16*40, 40, 40, 3, true, 0, 0);
        gc.drawImage(image, 80, 0, 40, 40, player.getX(),player.getY(),40,40);

        final long startTime = System.nanoTime();

        Image finalImage = image;


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

                /*String key = e.getCode().toString();
                if(!input.contains(key)){
                    input.add(key);
                }*/
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
                /*String key = e.getCode().toString();
                input.remove(key);*/
            }
        });

        new AnimationTimer(){
            public void handle(long currentTime){
                double t = (currentTime - startTime ) / 1000000000.0;

                Sprite playerSprite = new Sprite(finalImage, player);
                for (int i = 0; i < sprites.size(); i++) {
                    if(playerSprite.collision(sprites.get(i))){
                        double currentXVelo = player.getXVelo();
                        double currentYVelo = player.getYVelo();
                        System.out.println(playerSprite.getBoundary());
                    }
                }


                player.setVelo(0,0);
                if (upPressed.getValue()){
                    player.setVelo(0,-3);
                }if (downPressed.getValue()){
                    player.setVelo(0,3);
                }if (leftPressed.getValue()){
                    player.setVelo(-3,0);
                }if (rightPressed.getValue()){
                    player.setVelo(3,0);
                }

                /*if (input.contains("UP")){
                    player.setVelo(0,-3);
                }if (input.contains("DOWN")){
                    player.setVelo(0,3);
                }if (input.contains("RIGHT")){
                    player.setVelo(3,0);
                } if (input.contains("LEFT")){
                    player.setVelo(-3,0);
                }*/


                player.posUpdate();

                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                try {
                    level1.drawBoard(gc);
                }catch (Exception e){
                    e.printStackTrace();
                }
                //System.out.println("x: " + player.getX() + ", y: " + player.getY() + input);
                playerSprite.render(gc, 80, 0);


            }
        }.start();
    }
}

