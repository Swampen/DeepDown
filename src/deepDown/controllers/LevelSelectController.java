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

    public void loadLevel(int level)throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/level.fxml"));
        LevelController controller = new LevelController(level, 0, 3);
        loader.setController(controller);
        Parent root = loader.load();
        anchor.getChildren().setAll(root);
    }

    public void level1Selected()throws IOException{
        loadLevel(1);
    }

    public void level2Selected()throws IOException{
        loadLevel(2);
    }

    public void level3Selected()throws IOException{
        loadLevel(3);
    }
}