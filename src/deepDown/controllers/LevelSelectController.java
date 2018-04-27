package deepDown.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LevelSelectController {

    @FXML
    private AnchorPane anchor;

    @FXML
    public void initialize() {
    }

    public void loadLevel(int level) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/level.fxml"));
            LevelController controller = new LevelController(level, 0, 3);
            loader.setController(controller);
            Parent root = null;
            root = loader.load();
            anchor.getChildren().setAll(root);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void level1Selected() {
        loadLevel(1);
    }

    public void level2Selected() {
        loadLevel(2);
    }

    public void level3Selected(){
        loadLevel(3);
    }
}