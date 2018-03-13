package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class Controller {

    @FXML
    protected Canvas canvas;
    @FXML
    protected AnchorPane anchor;
    protected GraphicsContext gc;
    private Main main;

    @FXML
    public void initialize() {
        main = new Main();
        gc = canvas.getGraphicsContext2D();
    }


    public void level1Selected()throws IOException{
        main.root = FXMLLoader.load(getClass().getResource("level1.fxml"));
        anchor.getChildren().setAll(main.root);
    }
}
