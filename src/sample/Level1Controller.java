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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.Key;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.scene.input.KeyCode.S;
import static javafx.scene.input.KeyCode.W;

public class Level1Controller {

    @FXML
    protected Canvas canvas;
    @FXML
    protected AnchorPane anchor;
    @FXML
    protected Pane pane;
    protected GraphicsContext gc;

    @FXML
    public void initialize() {
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
        gc.drawImage(image, 80, 0, 40, 40, player.x,player.y,40,40);

        final long startTime = System.nanoTime();

        Image finalImage = image;
        new AnimationTimer(){
            public void handle(long currentTime){
                double t = (currentTime - startTime ) / 1000000000.0;

                canvas.setFocusTraversable(true);
                canvas.setOnKeyPressed(event -> {
                    switch (event.getCode()) {
                        case W:
                            player.addVelocity(0, -5);
                            System.out.println("W");
                            break;
                        case S:
                            player.addVelocity(0, 5);
                            System.out.println("S");
                            break;
                        case D:
                            player.addVelocity(5, 0);
                            System.out.println("D");
                            break;
                        case A:
                            player.addVelocity(-5, 0);
                            System.out.println("A");
                            break;
                        default:
                            break;
                    }
                    player.posUpdate(t);
                    gc.drawImage(finalImage, 80, 0, 40, 40, player.x,player.y,40,40);
                    player.setVelo(0,0);
                });
            }
        }.start();


    }
}

