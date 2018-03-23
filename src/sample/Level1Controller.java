package sample;

import com.sun.jdi.LongValue;
import com.sun.jdi.Type;
import com.sun.jdi.VirtualMachine;
import javafx.animation.AnimationTimer;
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
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class Level1Controller {

    @FXML
    protected Canvas canvas;
    @FXML
    protected AnchorPane anchor;
    @FXML
    protected Pane pane;
    protected GraphicsContext gc;

    @FXML
    public void initialize() throws IOException{
        gc = canvas.getGraphicsContext2D();
        GameBoard level1 = new GameBoard(1);
        Image image = null;
        try {
            level1.drawBoard(gc);
            image = new Image(new FileInputStream("src/sample/DeepDownTileSet.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Avatar player = new Avatar(1*40, 16*40, 40, 40, 3, true, 0, 0);
        gc.drawImage(image, 80, 0, 40, 40, player.getX(),player.getY(),40,40);

        final long startTime = System.nanoTime();

        Image finalImage = image;

        ArrayList<String> input = new ArrayList<String>();

        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                String key = e.getCode().toString();

                if(!input.contains(key)){
                    input.add(key);
                }
            }
        });

        canvas.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                String key = e.getCode().toString();
                input.remove(key);
            }
        });

        new AnimationTimer(){
            public void handle(long currentTime){
                double t = (currentTime - startTime ) / 1000000000.0;

                player.setVelo(0,0);
                if (input.contains("UP")){
                    player.setVelo(0,-5);
                }else if (input.contains("DOWN")){
                    player.setVelo(0,5);
                }else if (input.contains("RIGHT")){
                    player.setVelo(5,0);
                }else if (input.contains("LEFT")){
                    player.setVelo(-5,0);
                }

                player.posUpdate();

                try {
                    level1.drawBoard(gc);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(input);

                gc.clearRect(player.getX(), player.getY(), 40, 40);
                gc.drawImage(finalImage, 80, 0, 40, 40, player.getX(), player.getY(),40,40);
                System.out.println(player.getX());
                System.out.println(player.getY());
            }
        }.start();


    }
}

