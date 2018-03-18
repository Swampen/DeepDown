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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("level1.fxml"));
        Level1Controller controller = new Level1Controller();
        loader.setController(controller);
        main.root = loader.load();
        anchor.getChildren().setAll(main.root);
    }
}
