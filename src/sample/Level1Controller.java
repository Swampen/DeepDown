package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.security.Key;

public class Level1Controller {

    @FXML
    protected Canvas canvas;
    @FXML
    protected AnchorPane anchor;
    @FXML
    protected Pane pane;
    protected GraphicsContext gc;

    @FXML
    private void initialize() throws IOException{
       gc = canvas.getGraphicsContext2D();
       GameBoard level1 = new GameBoard(1);
       level1.drawBoard(pane);

        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(e -> {
            switch (e.getCode()){
                case W:
                    System.out.println("W");
                    break;
                case A:
                    System.out.println("A");
                    break;
                case S:
                    System.out.println("S");
                    break;
                case D:
                    System.out.println("D");
                    break;
                default:
                    System.out.println("other key");
                    break;

            }
        });
    }
}

