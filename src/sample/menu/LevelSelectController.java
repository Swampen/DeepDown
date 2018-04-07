package sample.menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import sample.Level1Controller;
import sample.Main;

import java.io.IOException;

public class LevelSelectController {

    @FXML
    protected AnchorPane anchor;
    private Main main;

    @FXML
    public void initialize() {
        main = new Main();
    }


    public void level1Selected()throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/resource/level1.fxml"));
        Level1Controller controller = new Level1Controller();
        loader.setController(controller);
        Parent root = main.getRoot();
        root = loader.load();
        anchor.getChildren().setAll(root);
    }
}
