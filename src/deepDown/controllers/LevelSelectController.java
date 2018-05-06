package deepDown.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LevelSelectController {

    @FXML
    private AnchorPane anchor;

    public void loadLevel(int level) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/level.fxml"));
            LevelController controller = new LevelController(level, 0, 3);
            loader.setController(controller);
            Parent root = loader.load();
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

    public void level4Selected(){
        loadLevel(4);
    }

    public void level5Selected(){
        loadLevel(5);
    }

    public void level6Selected(){
        loadLevel(6);
    }

    public void level7Selected(){
        loadLevel(7);
    }

    public void level8Selected(){
        loadLevel(8);
    }
}