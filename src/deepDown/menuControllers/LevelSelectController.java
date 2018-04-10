package deepDown.menuControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import deepDown.LevelController;
import deepDown.Main;

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/level1.fxml"));
        LevelController level = new LevelController(1);
        loader.setController(level);
        Parent root = main.getRoot();
        root = loader.load();
        anchor.getChildren().setAll(root);
    }

    public void level2Selected()throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/level2.fxml"));
        LevelController level = new LevelController(2);
        loader.setController(level);
        Parent root = main.getRoot();
        root = loader.load();
        anchor.getChildren().setAll(root);
    }
}