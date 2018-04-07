package sample;

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


public class Level1Controller {

    @FXML
    protected Canvas canvas;
    @FXML
    protected AnchorPane anchor;
    protected GraphicsContext gc;
    @FXML
    protected Label scoreLabel;
    private int score = 2000;
    private Image image;



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

        Avatar player = new Avatar(1*40, 16*40, 30, 30, 3, true, 0, 0);
        //gc.drawImage(image, 80, 0, 40, 40, player.getX(),player.getY(),40,40);

        final long startTime = System.nanoTime();

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

        new AnimationTimer(){
            public void handle(long currentTime){
                double t = (currentTime - startTime ) / 1000000000.0;

                int currentScore = score-(int)t;

                scoreLabel.setText(Integer.toString(currentScore));


                Sprite playerSprite = new Sprite(image, player, 4, 0, 40);
                for (int i = 0; i < sprites.size(); i++) {
                    if(playerSprite.collision(sprites.get(i)) && sprites.get(i).getType() == 1){
                        player.setVelo(0,0);
                        player.setPos(player.getPrevX(),player.getPrevY());
                        break;

                        //System.out.println(playerSprite.getBoundary());
                    }

                    if (playerSprite.collision(sprites.get(i)) && sprites.get(i).getType() == 2){
                        System.out.println("DING! you got a coin!");
                        sprites.remove(sprites.get(i));
                    }
                }


                    player.setVelo(0, 0);
                    if (upPressed.getValue()) {
                        player.setyVelo(-3);
                    }
                    if (downPressed.getValue()) {
                        player.setyVelo(3);
                    }
                    if (leftPressed.getValue()) {
                        player.setxVelo(-3);
                    }
                    if (rightPressed.getValue()) {
                        player.setxVelo(3);
                    }
                player.posUpdate();

                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                /*try {
                    level1.drawBoard(gc);
                }catch (Exception e){
                    e.printStackTrace();
                }*/
                level1.renderSprite(sprites, gc);
                playerSprite.renderPlayer(gc);


            }
        }.start();
    }
}

